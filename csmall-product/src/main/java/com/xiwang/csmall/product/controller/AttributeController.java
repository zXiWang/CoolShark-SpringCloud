package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.service.AttributeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 属性(Attribute)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:15:48
 */
@RestController
@RequestMapping("/attribute")
public class AttributeController {
    /**
     * 服务对象
     */
    @Resource
    private AttributeService attributeService;


}

