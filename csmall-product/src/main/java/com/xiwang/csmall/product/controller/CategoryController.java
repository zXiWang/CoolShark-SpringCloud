package com.xiwang.csmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xiwang.csmall.product.pojo.dto.CategoryAddNewDTO;
import com.xiwang.csmall.product.pojo.vo.CategoryListItemVO;
import com.xiwang.csmall.product.service.CategoryService;
import com.xiwang.csmall.product.web.JsonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    public CategoryController() {
        log.info("创建控制器对象：CategoryController");
    }


    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    @ApiOperation("添加类别")
    @ApiOperationSupport(order = 100)
    @PostMapping(value = "/addNew")
    public String addNew(CategoryAddNewDTO categoryAddNewDTO) {
        log.debug("开始测试添加类别请求,对象实例= {}", categoryAddNewDTO);
        categoryService.addNew(categoryAddNewDTO);
        return "类别添加成功!";
    }

    @ApiOperation("删除类别")
    @ApiOperationSupport(order = 200)
    @GetMapping("/delete")
    public String delete(Long id) {
        log.debug("开始测试删除类别请求,id={}", id);
        categoryService.delete(id);
        return "类别删除成功!";
    }

    @ApiOperation("查询类别列表")
    @ApiOperationSupport(order = 400)
    @GetMapping(value = "/list")
    public JsonResult<List<CategoryListItemVO>> list() {
        List<CategoryListItemVO> list = categoryService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("查询父级下的类别列表")
    @ApiOperationSupport(order = 401)
    @GetMapping(value = "/{id:[0-9]+}/list")
    public JsonResult<List<CategoryListItemVO>> listByParentId(@PathVariable Long id) {
        List<CategoryListItemVO> list = categoryService.listByParentId(id);
        return JsonResult.ok(list);
    }

}

