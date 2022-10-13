package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.mapper.AttributeMapper;
import com.xiwang.csmall.product.mapper.AttributeTemplateMapper;
import com.xiwang.csmall.product.pojo.dto.AttributeAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Attribute;
import com.xiwang.csmall.product.pojo.vo.AttributeListItemVO;
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
        //判断相关属性模板里有没有此名称
        if (attributeAddNewDTO
                .getName()
                .equals(attributeTemplateMapper
                        .getNormalById(attributeAddNewDTO
                                .getTemplateId())
                        .getName())){
            String message = "添加属性失败，尝试添加的相册名称已经被占用！";
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

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Attribute getNormalById(Long id) {
        return this.attributeMapper.getNormalById(id);
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
     * @return 实例对象
     */
    @Override
    public Attribute updateById(Attribute attribute) {
        this.attributeMapper.updateById(attribute);
        return this.getNormalById(attribute.getId());
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
        attributeMapper.deleteById(id);
    }
}
