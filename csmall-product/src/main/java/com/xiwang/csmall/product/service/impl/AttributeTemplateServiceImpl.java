package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.mapper.AttributeMapper;
import com.xiwang.csmall.product.mapper.AttributeTemplateMapper;
import com.xiwang.csmall.product.mapper.CategoryAttributeTemplateMapper;
import com.xiwang.csmall.product.pojo.dto.AttributeTemplateAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.AttributeTemplate;
import com.xiwang.csmall.product.pojo.vo.AttributeTemplateListItemVO;
import com.xiwang.csmall.product.service.AttributeTemplateService;
import com.xiwang.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 属性模版(AttributeTemplate)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:45
 */
@Slf4j
@Service("attributeTemplateService")
public class AttributeTemplateServiceImpl implements AttributeTemplateService {
    @Autowired
    private AttributeTemplateMapper attributeTemplateMapper;

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    CategoryAttributeTemplateMapper categoryAttributeTemplateMapper;
    /**
     * 添加属性模板
     *
     * @param attributeTemplateAddNewDTO 实例对象
     */
    @Override
    public void addNew(AttributeTemplateAddNewDTO attributeTemplateAddNewDTO) {
        if (attributeTemplateMapper.countByName(attributeTemplateAddNewDTO.getName()) != 0) {
            String message = "添加失败!名称重复!";
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        AttributeTemplate attributeTemplate = new AttributeTemplate();
        BeanUtils.copyProperties(attributeTemplateAddNewDTO, attributeTemplate);
        attributeTemplateMapper.insert(attributeTemplate);
    }

    @Override
    public void delete(Long id) {
        if (attributeTemplateMapper.getNormalById(id) == null) {
            String message = "删除失败!不存在该属性模板!";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        int rows = attributeTemplateMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除属性模板失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
        rows = attributeMapper.deleteByTemplateId(id);
        if (rows < 1) {
            String message = "删除属性模板失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }

        // 如果有属性关联到了此属性模板，不允许删除
        {
            int count = attributeMapper.countByTemplateId(id);
            if (count > 0) {
                String message = "删除属性模板失败！当前属性模板仍存在关联的属性！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }

        // 如果有类别关联到了此属性模板，不允许删除
        {
            int count = categoryAttributeTemplateMapper.countByAttributeTemplate(id);
            if (count > 0) {
                String message = "删除属性模板失败！当前属性模板仍存在关联的类别！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }
    }

    @Override
    public List<AttributeTemplateListItemVO> list() {
        return attributeTemplateMapper.list();
    }
}
