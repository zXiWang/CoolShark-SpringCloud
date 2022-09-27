package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.service.SkuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * SKU（Stock Keeping Unit）(Sku)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:41
 */
@RestController
@RequestMapping("/sku")
public class SkuController {
    /**
     * 服务对象
     */
    @Resource
    private SkuService skuService;


    
}

