package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.pojo.dto.AlbumAddNewDTO;
import com.xiwang.csmall.product.pojo.dto.CategoryAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Category;
import com.xiwang.csmall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 类别(Category)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:40
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    @PostMapping(value = "/addNew")
    public String addNew(CategoryAddNewDTO categoryAddNewDTO) {
        categoryService.addNew(categoryAddNewDTO);
        return "类别添加成功!";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        log.debug("开始测试删除类别请求,id={}",id);
        categoryService.delete(id);
        return "类别删除成功!";
    }
    
}

