package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.mapper.SkuSpecificationMapper;
import com.xiwang.csmall.product.pojo.entity.SkuSpecification;
import com.xiwang.csmall.product.pojo.vo.SkuSpecificationNormalVO;
import com.xiwang.csmall.product.service.SkuSpecificationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SKU数据(SkuSpecification)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
@Service("skuSpecificationService")
public class SkuSpecificationServiceImpl implements SkuSpecificationService {
    @Resource
    private SkuSpecificationMapper skuSpecificationMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SkuSpecificationNormalVO getNormalById(Long id) {
        return this.skuSpecificationMapper.getNormalById(id);
    }

    /**
     * 新增数据
     *
     * @param skuSpecification 实例对象
     * @return 实例对象
     */
    @Override
    public SkuSpecification insert(SkuSpecification skuSpecification) {
        this.skuSpecificationMapper.insert(skuSpecification);
        return skuSpecification;
    }

    /**
     * 修改数据
     *
     * @param skuSpecification 实例对象
     * @return 实例对象
     */
    @Override
    public SkuSpecificationNormalVO updateById(SkuSpecification skuSpecification) {
        this.skuSpecificationMapper.updateById(skuSpecification);
        return this.getNormalById(skuSpecification.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.skuSpecificationMapper.deleteById(id) > 0;
    }
}
