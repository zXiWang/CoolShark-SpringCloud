package com.xiwang.csmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xiwang.csmall.product.pojo.dto.AttributeTemplateAddNewDTO;
import com.xiwang.csmall.product.pojo.vo.AttributeTemplateListItemVO;
import com.xiwang.csmall.product.service.AttributeTemplateService;
import com.xiwang.csmall.product.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 属性模版(AttributeTemplate)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:36
 */
@Slf4j
@Api(tags = "02 属性模板管理")
@RestController
@RequestMapping("/attributeTemplate")
public class AttributeTemplateController {
    /**
     * 服务对象
     */
    @Resource
    private AttributeTemplateService attributeTemplateService;

    /**
     * @param attributeTemplateAddNewDTO 实例对象
     * @return 返回消息
     */
    @ApiOperation("添加属性模板")
    @ApiOperationSupport(order = 1)
    @PostMapping("/addNew")
    public JsonResult addNew(AttributeTemplateAddNewDTO attributeTemplateAddNewDTO) {
        log.debug("开始测试添加相册请求,对象实例= {}", attributeTemplateAddNewDTO);
        attributeTemplateService.addNew(attributeTemplateAddNewDTO);
        return JsonResult.ok();
    }

    /**
     * 删除属性模板
     *
     * @param id 主键id
     * @return 消息
     */
    @ApiOperation("删除属性模板")
    @ApiOperationSupport(order = 100)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id) {
        log.debug("开始测试删除属性模板请求,id={}", id);
        attributeTemplateService.delete(id);
        return JsonResult.ok();
    }

    @ApiOperation("查询属性模板列表")
    @ApiOperationSupport(order = 400)
    @GetMapping(value = "/list")
    public JsonResult<List<AttributeTemplateListItemVO>> list() {
        List<AttributeTemplateListItemVO> list = attributeTemplateService.list();
        return JsonResult.ok(list);
    }
}

