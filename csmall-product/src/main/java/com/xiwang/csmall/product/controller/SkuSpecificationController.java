package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.service.SkuSpecificationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * SKU数据(SkuSpecification)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:41
 */
@RestController
@RequestMapping("/skuSpecification")
public class SkuSpecificationController {
    /**
     * 服务对象
     */
    @Resource
    private SkuSpecificationService skuSpecificationService;


}

