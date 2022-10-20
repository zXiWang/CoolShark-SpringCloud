package com.xiwang.csmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xiwang.csmall.product.pojo.dto.BrandAddNewDTO;
import com.xiwang.csmall.product.pojo.vo.BrandListItemVO;
import com.xiwang.csmall.product.pojo.vo.BrandNormalVO;
import com.xiwang.csmall.product.service.BrandService;
import com.xiwang.csmall.product.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌(Brand)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:37
 */
@Slf4j
@Api(tags = "03 品牌管理")
@RestController
@RequestMapping("/brand")
public class BrandController {


    public BrandController() {
        log.info("创建控制器对象：BrandController");
    }

    /**
     * 服务对象
     */
    @Resource
    private BrandService brandService;

    /**
     * 添加品牌
     *
     * @param brandAddNewDTO 实例对象
     * @return 返回信息
     */
    @ApiOperation("添加品牌")
    @ApiOperationSupport(order = 1)
    @PostMapping("/addNew")
    public JsonResult addNew(BrandAddNewDTO brandAddNewDTO) {
        log.debug("品牌添加开始对象实例为: {}", brandAddNewDTO);
        brandService.addNew(brandAddNewDTO);
        return JsonResult.ok();
    }

    /**
     * 删除品牌
     *
     * @param id 主键id
     * @return 消息
     */
    @ApiOperation("删除品牌")
    @ApiOperationSupport(order = 100)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id) {
        log.debug("品牌删除开始id= {}", id);
        brandService.delete(id);
        return JsonResult.ok();
    }

    @ApiOperation("启用品牌")
    @ApiOperationSupport(order = 301)
    @PostMapping("/{id:[0-9]+}/enable")
    public JsonResult enable(@PathVariable Long id) {
        log.debug("开始处理修改品牌: id={}", id);
        brandService.setEnabled(id);
        return JsonResult.ok();
    }

    @ApiOperation("禁用品牌")
    @ApiOperationSupport(order = 301)
    @PostMapping("/{id:[0-9]+}/disable")
    public JsonResult disable(@PathVariable Long id) {
        log.debug("开始处理修改品牌: id={}", id);
        brandService.setDisabled(id);
        return JsonResult.ok();
    }

    @ApiOperation("查询品牌列表")
    @ApiOperationSupport(order = 400)
    @GetMapping(value = "/list")
    public JsonResult<List<BrandListItemVO>> list() {
        List<BrandListItemVO> list = brandService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("根据id查询品牌详情")
    @ApiOperationSupport(order = 401)
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<BrandNormalVO> getNormalById(@PathVariable Long id) {
        log.debug("开始处理【根据id查询品牌详情】的请求，参数：{}", id);
        BrandNormalVO brand = brandService.getNormalById(id);
        return JsonResult.ok(brand);
    }
}



