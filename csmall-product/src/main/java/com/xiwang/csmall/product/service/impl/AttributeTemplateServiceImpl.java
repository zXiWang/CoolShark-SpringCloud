package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.mapper.AttributeTemplateMapper;
import com.xiwang.csmall.product.pojo.dto.AttributeTemplateAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.AttributeTemplate;
import com.xiwang.csmall.product.service.AttributeTemplateService;
import com.xiwang.csmall.product.web.JsonResult;
import com.xiwang.csmall.product.web.ServiceCode;
import org.springframework.beans.BeanUtils;
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
            String message="添加失败!名称重复!";
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        AttributeTemplate attributeTemplate=new AttributeTemplate();
        BeanUtils.copyProperties(attributeTemplateAddNewDTO,attributeTemplate);
        attributeTemplateMapper.insert(attributeTemplate);
    }

    public void delete(Long id){
        if(attributeTemplateMapper.getNormalById(id)==null){
            String message="删除失败!不存在该属性模板!";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        attributeTemplateMapper.deleteById(id);
    }
}
