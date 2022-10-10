package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.dto.AttributeTemplateAddNewDTO;
import com.xiwang.csmall.product.pojo.vo.AttributeTemplateListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 属性模版(AttributeTemplate)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:45
 */
public interface AttributeTemplateService {


    @Transactional
    void addNew(AttributeTemplateAddNewDTO attributeTemplateAddNewDTO);

    @Transactional
    void delete(Long id);

    List<AttributeTemplateListItemVO> list();
}
