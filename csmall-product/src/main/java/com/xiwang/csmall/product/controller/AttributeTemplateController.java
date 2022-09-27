package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.pojo.dto.AttributeTemplateAddNewDTO;
import com.xiwang.csmall.product.service.AttributeTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 属性模版(AttributeTemplate)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:36
 */
@Slf4j
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
    @RequestMapping("/addNew")
    public String addNew(AttributeTemplateAddNewDTO attributeTemplateAddNewDTO) {
        attributeTemplateService.addNew(attributeTemplateAddNewDTO);
        return "属性模板添加成功!";

    }

    /**
     * 删除属性模板
     * @param id 主键id
     * @return 消息
     */
    @RequestMapping("/delete")
    public String delete(Long id) {
        log.debug("开始测试删除属性模板请求,id={}", id);
        attributeTemplateService.delete(id);
        return "属性模板删除成功!";
    }
}

