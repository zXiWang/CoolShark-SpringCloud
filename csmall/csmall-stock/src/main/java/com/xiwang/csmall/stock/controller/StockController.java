package com.xiwang.csmall.stock.controller;


import com.xiwang.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import com.xiwang.csmall.commons.restful.JsonResult;
import com.xiwang.csmall.stock.service.IStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@Api(tags = "库存管理模块")
@RequestMapping("/base/stock")
public class StockController {
    @Autowired
    private IStockService stockService;

    @PostMapping("/reduce/count")
    @ApiOperation("减少商品库存数")
    public JsonResult reduceCommodityCount(
            StockReduceCountDTO stockReduceCountDTO) {
        // 调用业务逻辑层
        stockService.reduceCommodityCount(stockReduceCountDTO);
        return JsonResult.ok("库存减少完成!");
    }
}

