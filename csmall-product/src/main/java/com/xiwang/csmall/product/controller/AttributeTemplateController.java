package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.pojo.dto.AttributeTemplateAddNewDTO;
import com.xiwang.csmall.product.service.AttributeTemplateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 属性模版(AttributeTemplate)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:36
 */
@RestController
@RequestMapping("/attributeTemplate")
public class AttributeTemplateController {
    /**
     * 服务对象
     */
    @Resource
    private AttributeTemplateService attributeTemplateService;

    /**
     *
     * @param attributeTemplateAddNewDTO 实例对象
     * @return 返回消息
     */
    @RequestMapping("/addNew")
    public String addNew(AttributeTemplateAddNewDTO attributeTemplateAddNewDTO){
        try{
            attributeTemplateService.addNew(attributeTemplateAddNewDTO);
            return "属性模板添加成功!";
        }catch (RuntimeException e){
            return "名称重复!添加失败!";
        }

    }
    
}

