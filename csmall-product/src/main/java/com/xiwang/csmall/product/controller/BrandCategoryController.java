package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.service.BrandCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 品牌与类别关联(BrandCategory)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:37
 */
@RestController
@RequestMapping("/brandCategory")
public class BrandCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private BrandCategoryService brandCategoryService;


}

