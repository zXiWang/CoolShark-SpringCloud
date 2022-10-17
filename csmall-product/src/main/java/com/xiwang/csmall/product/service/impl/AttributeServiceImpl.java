package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.mapper.AttributeMapper;
import com.xiwang.csmall.product.mapper.AttributeTemplateMapper;
import com.xiwang.csmall.product.pojo.dto.AttributeAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Attribute;
import com.xiwang.csmall.product.pojo.vo.AttributeListItemVO;
import com.xiwang.csmall.product.pojo.vo.AttributeTemplateNormalVO;
import com.xiwang.csmall.product.service.AttributeService;
import com.xiwang.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 属性(Attribute)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:44
 */
@Slf4j
@Service("attributeService")
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private AttributeTemplateMapper attributeTemplateMapper;

    @Override
    public void addNew(AttributeAddNewDTO attributeAddNewDTO) {
        AttributeTemplateNormalVO attributeTemplateNormalVO = attributeTemplateMapper.getNormalById(attributeAddNewDTO.getTemplateId());
        //查询有无此属性模板
        if (attributeTemplateNormalVO == null) {
            String message = "添加属性失败，未找到该属性模板！";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        //判断相关属性模板里有没有此名称
        int rows = attributeMapper.countByNameAndTemplateId(attributeAddNewDTO);
        if (rows != 0) {
            String message = "添加属性失败，尝试添加的属性名称已经被占用！";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        Attribute attribute = new Attribute();
        BeanUtils.copyProperties(attributeAddNewDTO, attribute);
        attributeMapper.insert(attribute);
    }

    @Override
    public List<AttributeListItemVO> list() {
        return attributeMapper.list();
    }

    @Override
    public List<AttributeListItemVO> listByTemplateId(Long templateId) {
        return attributeMapper.listByTemplateId(templateId);
    }

    /**
     * 新增数据
     *
     * @param attribute 实例对象
     * @return 实例对象
     */
    @Override
    public Attribute insert(Attribute attribute) {
        this.attributeMapper.insert(attribute);
        return attribute;
    }

    /**
     * 修改数据
     *
     * @param attribute 实例对象
     */
    @Override
    public void updateById(Attribute attribute) {
        attributeMapper.updateById(attribute);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public void delete(Long id) {
        if (attributeMapper.getNormalById(id) == null) {
            String message = "删除失败!不存在该属性!";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        int rows = attributeMapper.deleteById(id);
        if (rows != 1) {
            String message = "服务器未响应!删除失败!";
            throw new ServiceException(ServiceCode.ERR_DELETE, message);

        }
    }
}
