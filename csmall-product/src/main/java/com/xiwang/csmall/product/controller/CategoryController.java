package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 类别(Category)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:40
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;


    
}

