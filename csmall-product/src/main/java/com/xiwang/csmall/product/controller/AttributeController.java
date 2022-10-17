package com.xiwang.csmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xiwang.csmall.product.pojo.dto.AttributeAddNewDTO;
import com.xiwang.csmall.product.pojo.vo.AttributeListItemVO;
import com.xiwang.csmall.product.service.AttributeService;
import com.xiwang.csmall.product.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 属性(Attribute)表控制层
 *
 * @author 夕妄
 * @since 2022 -09-26 17:15:48
 */
@Slf4j
@Api(tags = "01 属性管理")
@Validated
@RestController
@RequestMapping("/attribute")
public class AttributeController {
    /**
     * 服务对象
     */
    @Resource
    private AttributeService attributeService;

    @ApiOperation("查询属性列表")
    @ApiOperationSupport(order = 400)
    @GetMapping(value = "/list")
    public JsonResult<List<AttributeListItemVO>> list() {
        List<AttributeListItemVO> list = attributeService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("查询属性模板的属性列表")
    @ApiOperationSupport(order = 401)
    @GetMapping(value = "/{id:[0-9]+}/list")
    public JsonResult<List<AttributeListItemVO>> listByTemplateId(@PathVariable Long id) {
        List<AttributeListItemVO> list = attributeService.listByTemplateId(id);
        return JsonResult.ok(list);
    }

    @ApiOperation("添加属性")
    @ApiOperationSupport(order = 1)
    @PostMapping(value = "/addNew")
    public JsonResult<List<AttributeAddNewDTO>> addNew(@Validated AttributeAddNewDTO attributeAddNewDTO) {
        log.debug("开始处理【添加属性】的请求，参数：{}", attributeAddNewDTO);
        attributeService.addNew(attributeAddNewDTO);
        return JsonResult.ok();
    }

    //    @Deprecated
    @ApiOperation("删除属性")
    @ApiImplicitParam(name = "id", value = "属性id", required = true, dataType = "long")
    @ApiOperationSupport(order = 100)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id) {
        log.debug("开始测试删除属性请求,id={}", id);
        attributeService.delete(id);
        return JsonResult.ok();
    }


}

