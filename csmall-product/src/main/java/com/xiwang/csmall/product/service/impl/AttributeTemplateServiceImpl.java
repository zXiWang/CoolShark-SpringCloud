package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.mapper.AttributeTemplateMapper;
import com.xiwang.csmall.product.pojo.dto.AttributeTemplateAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.AttributeTemplate;
import com.xiwang.csmall.product.service.AttributeTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 属性模版(AttributeTemplate)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:45
 */
@Service("attributeTemplateService")
public class AttributeTemplateServiceImpl implements AttributeTemplateService {
    @Resource
    private AttributeTemplateMapper attributeTemplateMapper;

    /**
     * 添加属性模板
     * @param attributeTemplateAddNewDTO 实例对象
     *
     */
    public void addNew(AttributeTemplateAddNewDTO attributeTemplateAddNewDTO) {
        if(attributeTemplateMapper.countByName(attributeTemplateAddNewDTO.getName())!=0){
            throw new ServiceException("添加失败!名称重复!");
        }
        AttributeTemplate attributeTemplate=new AttributeTemplate();
        attributeTemplate.setName(attributeTemplateAddNewDTO.getName());
        attributeTemplate.setKeywords(attributeTemplateAddNewDTO.getKeywords());
        attributeTemplate.setSort(attributeTemplateAddNewDTO.getSort());
        attributeTemplateMapper.insert(attributeTemplate);
    }

    public void delete(Long id){
        if(attributeTemplateMapper.getNormalById(id)==null){
            throw new ServiceException("删除失败!不存在该属性模板!");
        }
        attributeTemplateMapper.delete(id);
    }
}
