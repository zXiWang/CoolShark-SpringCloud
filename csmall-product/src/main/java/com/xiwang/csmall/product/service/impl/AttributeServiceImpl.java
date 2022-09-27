package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.mapper.AttributeMapper;
import com.xiwang.csmall.product.pojo.entity.Attribute;
import com.xiwang.csmall.product.service.AttributeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 属性(Attribute)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:44
 */
@Service("attributeService")
public class AttributeServiceImpl implements AttributeService {
    @Resource
    private AttributeMapper attributeMapper;

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
    public boolean deleteById(Long id) {
        return this.attributeMapper.deleteById(id) > 0;
    }
}
