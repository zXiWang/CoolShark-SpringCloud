package cn.tedu.mall.order.service;


import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.order.mapper.OmsCartMapper;
import cn.tedu.mall.pojo.order.dto.CartAddDTO;
import cn.tedu.mall.pojo.order.dto.CartUpdateDTO;
import cn.tedu.mall.pojo.order.model.OmsCart;
import cn.tedu.mall.pojo.order.vo.CartStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OmsCartServiceImpl implements IOmsCartService {

    @Autowired
    private OmsCartMapper omsCartMapper;

    @Override
    public void addCart(CartAddDTO cartDTO) {

        // 要查询购物车中是否有指定商品之前,必须确定用户的Id
        Long userId = getUserId();
        log.debug("得到userId="+userId);
        // 根据用户Id和商品skuId,查询商品信息
        OmsCart omsCart = omsCartMapper.selectExistsCart(
                userId, cartDTO.getSkuId());
        // 判断该商品是否存在
        if (omsCart == null) {
            // 如果omsCart为null,表示当前用户没有将这个商品新增到购物车
            // 所以执行新增操作,新增操作需要一个OmsCart对象
            OmsCart newCart = new OmsCart();
            // 将参数CartAddDTO对象中的同名属性赋值给newCart
            BeanUtils.copyProperties(cartDTO, newCart);
            // CartAddDTO中没有userId属性,需要单独赋值
            newCart.setUserId(userId);
            // 执行新增操作
            omsCartMapper.saveCart(newCart);
        } else {
            // 如果omsCart不是null,表示当前用户已经将这个商品新增到购物车中了
            // 我们需要做的就是将这次新增的数量和原有的数量相加,保存到数据库中
            // 我们写的mapper方法是直接修改商品数量的值
            // 所以要在java代码层面完成本次业务数量的相加操作
            omsCart.setQuantity(omsCart.getQuantity() + cartDTO.getQuantity());
            // 确定了数量之后,直接调用修改购物车数量方法即可
            omsCartMapper.updateQuantityById(omsCart);
        }
    }

    @Override
    public JsonPage<CartStandardVO> listCarts(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public void removeCart(Long[] ids) {

    }

    @Override
    public void removeAllCarts() {

    }

    @Override
    public void removeUserCarts(OmsCart omsCart) {

    }

    @Override
    public void updateQuantity(CartUpdateDTO cartUpdateDTO) {

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
