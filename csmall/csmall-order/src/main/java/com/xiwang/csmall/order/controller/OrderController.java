package com.xiwang.csmall.order.controller;

import com.xiwang.csmall.commons.pojo.order.dto.OrderAddDTO;
import com.xiwang.csmall.commons.restful.JsonResult;
import com.xiwang.csmall.order.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@Api(tags = "订单管理模块")
@RequestMapping("/base/order")
public class OrderController {
    @Autowired
    IOrderService iOrderService;

    @PostMapping("/add")
    @ApiOperation("新增订单功能")
    public JsonResult orderAdd(OrderAddDTO orderAddDTO) {
        iOrderService.orderAdd(orderAddDTO);
        return JsonResult.ok("新增订单完成!");
    }
}
