package cn.tedu.mall.order.service;


import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.order.mapper.OmsOrderItemMapper;
import cn.tedu.mall.order.mapper.OmsOrderMapper;
import cn.tedu.mall.order.utils.IdGeneratorUtils;
import cn.tedu.mall.pojo.order.dto.OrderAddDTO;
import cn.tedu.mall.pojo.order.dto.OrderItemAddDTO;
import cn.tedu.mall.pojo.order.dto.OrderListTimeDTO;
import cn.tedu.mall.pojo.order.dto.OrderStateUpdateDTO;
import cn.tedu.mall.pojo.order.model.OmsCart;
import cn.tedu.mall.pojo.order.model.OmsOrder;
import cn.tedu.mall.pojo.order.model.OmsOrderItem;
import cn.tedu.mall.pojo.order.vo.OrderAddVO;
import cn.tedu.mall.pojo.order.vo.OrderDetailVO;
import cn.tedu.mall.pojo.order.vo.OrderListVO;
import cn.tedu.mall.product.service.order.IForOrderSkuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


// 后面的秒杀业务需要调用这个生成订单的方法,需要支持dubbo调用
@DubboService
@Service
@Slf4j
public class OmsOrderServiceImpl implements IOmsOrderService {

    @DubboReference
    private IForOrderSkuService dubboSkuService;
    @Autowired
    private IOmsCartService omsCartService;
    @Autowired
    private OmsOrderMapper omsOrderMapper;
    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;

    @GlobalTransactional
    @Override
    public OrderAddVO addOrder(OrderAddDTO orderAddDTO) {
        // 一.收集信息
        OmsOrder order = new OmsOrder();
        BeanUtils.copyProperties(orderAddDTO, order);
        // OrderAddDTO的属性比OmsOrder少,所以需要编写一个方法来进行数据的收集
        loadOrder(order);
        // 到此为止,order的普通属性全部赋值完毕
        // 下面将参数orderAddDTO中包含的订单项(orderItem)信息赋值
        List<OrderItemAddDTO> orderItemAddDTOs = orderAddDTO.getOrderItems();
        if (orderItemAddDTOs == null) {
            throw new CoolSharkServiceException(
                    ResponseCode.BAD_REQUEST,
                    "订单中必须至少包含一件商品"
            );
        }
        List<OmsOrderItem> omsOrderItems = new ArrayList<>();
        for (OrderItemAddDTO addDTO : orderItemAddDTOs) {
            OmsOrderItem orderItem = new OmsOrderItem();
            BeanUtils.copyProperties(addDTO, orderItem);
            Long itemId = IdGeneratorUtils.getDistributeId("order_item");
            orderItem.setOrderId(order.getId());
            omsOrderItems.add(orderItem);
            // 二.执行操作数据库的指令
            Long skuId = orderItem.getSkuId();
            // 1.减库存
            int rows = dubboSkuService.reduceStockNum(
                    skuId, orderItem.getQuantity()
            );
            if (rows == 0) {
                log.warn("商品库存不足,skuId:{}", skuId);
                // 库存不足不能继续生成订单,抛出异常,终止事物并回滚
                throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "库存不足!");
            }
            // 2.删除勾选的购物车商品
            OmsCart omsCart = new OmsCart();
            omsCart.setUserId(order.getUserId());
            omsCart.setSkuId(skuId);
            omsCartService.removeUserCarts(omsCart);
        }
        // 3.执行新增订单
        // omsOrderMapper直接调用新增订单的方法
        omsOrderMapper.insertOrder(order);
        omsOrderItemMapper.insertOrderItemList(omsOrderItems);
        // 三.准备返回值,返回给前端
        OrderAddVO addVO = new OrderAddVO();
        addVO.setId(order.getId());
        addVO.setSn(order.getSn());
        addVO.setCreateTime(order.getGmtCreate());
        addVO.setPayAmount(order.getAmountOfActualPay());
        return null;
    }

    private void loadOrder(OmsOrder order) {
        // 给id赋值,使用Leaf分布式序列生成系统
        Long id = IdGeneratorUtils.getDistributeId("order");
        order.setId(id);
        // 生成订单号,直接使用UUID
        order.setSn(UUID.randomUUID().toString());

        // 赋值userId,因为秒杀业务会自动生成userId
        // 所以这里先判断是否有值
        if (order.getUserId() == null) {
            order.setUserId(getUserId());
        }
        // 为订单状态赋值
        // 订单状态如果为null,将其设默认值0,表示未支付
        if (order.getState() == null) {
            order.setState(0);
        }

        // 为了保证下单时间和数据创建时间和最后修改时间一致
        // 赋同样的值
        LocalDateTime now = LocalDateTime.now();
        order.setGmtOrder(now);
        order.setGmtCreate(now);
        order.setGmtModified(now);

        // 验算实际支付金额
        // 计算公式:   实际支付金额=原价-优惠+运费
        // 数据类型使用BigDecimal,防止浮点偏移,还有更大的取值范围
        BigDecimal price = order.getAmountOfOriginalPrice();
        BigDecimal freight = order.getAmountOfFreight();
        BigDecimal discount = order.getAmountOfDiscount();
        BigDecimal actualPay = price.subtract(discount).add(freight);
        // 最后将计算完成的实际支付金额赋值给order
        order.setAmountOfActualPay(actualPay);
    }

    @Override
    public void updateOrderState(OrderStateUpdateDTO orderStateUpdateDTO) {
        OmsOrder order = new OmsOrder();
        BeanUtils.copyProperties(orderStateUpdateDTO, order);
        omsOrderMapper.updateOrderById(order);
    }

    @Override
    public JsonPage<OrderListVO> listOrdersBetweenTimes(OrderListTimeDTO orderListTimeDTO) {
        validateTimeAndLoadTime(orderListTimeDTO);
        // 赋值UserId
        orderListTimeDTO.setUserId(getUserId());
        // 分页查询设置分页条件
        PageHelper.startPage(orderListTimeDTO.getPage(), orderListTimeDTO.getPageSize());

        List<OrderListVO> list = omsOrderMapper.selectOrdersBetweenTimes(orderListTimeDTO);

        return JsonPage.restPage(new PageInfo<>(list));

    }

    private void validateTimeAndLoadTime(OrderListTimeDTO orderListTimeDTO) {
        LocalDateTime startTime = orderListTimeDTO.getStartTime();
        LocalDateTime endTime = orderListTimeDTO.getEndTime();
        // 为了方便,设置两个时间任意一个为空时,就查询最近一个月的订单
        if (startTime == null || endTime == null) {
            startTime = LocalDateTime.now().minusMonths(1);
            endTime = LocalDateTime.now();
            orderListTimeDTO.setStartTime(startTime);
            orderListTimeDTO.setEndTime(endTime);

        } else {
            if (endTime.toInstant(ZoneOffset.of("+8")).toEpochMilli() <
                    startTime.toInstant(ZoneOffset.of("+8")).toEpochMilli()) {
                throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,
                        "结束时间大于开始时间!");
            }
        }
    }

    @Override
    public OrderDetailVO getOrderDetail(Long id) {
        return null;
    }

    // 业务逻辑层中有获得当前用户登录信息的需求
    public CsmallAuthenticationInfo getUserInfo() {
        // 编写SpringSecurity上下文中获得用户的代码
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();
        if (authenticationToken == null) {
            throw new CoolSharkServiceException(
                    ResponseCode.UNAUTHORIZED, "您没有登录!");
        }
        // 不为null
        CsmallAuthenticationInfo csmallAuthenticationInfo =
                (CsmallAuthenticationInfo) authenticationToken.getCredentials();
        return csmallAuthenticationInfo;
    }

    public long getUserId() {
        return getUserInfo().getId();
    }
}
