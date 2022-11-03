package cn.tedu.mall.order.controller;


import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.order.service.IOmsOrderService;
import cn.tedu.mall.pojo.order.dto.OrderAddDTO;
import cn.tedu.mall.pojo.order.vo.OrderAddVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "订单管理模块")
@RequestMapping("/oms/order")
public class OmsOrderController {
    @Autowired
    private IOmsOrderService omsOrderService;

    @PostMapping("/add")
    @ApiOperation("执行新增订单方法")
    @PreAuthorize("hasRole('user')")
    public JsonResult<OrderAddVO> addOrder(@Validated OrderAddDTO orderAddDTO) {
        OrderAddVO orderAddVO = omsOrderService.addOrder(orderAddDTO);
        return JsonResult.ok(orderAddVO);
    }
}
