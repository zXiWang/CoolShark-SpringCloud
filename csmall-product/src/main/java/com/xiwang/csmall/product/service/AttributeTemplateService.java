package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.dto.AttributeTemplateAddNewDTO;

/**
 * 属性模版(AttributeTemplate)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:45
 */
public interface AttributeTemplateService {


    void addNew(AttributeTemplateAddNewDTO attributeTemplateAddNewDTO);

    void delete(Long id);
}
