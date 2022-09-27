package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.pojo.entity.Sku;
import com.xiwang.csmall.product.mapper.SkuMapper;
import com.xiwang.csmall.product.pojo.vo.SkuNormalVO;
import com.xiwang.csmall.product.service.SkuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SKU（Stock Keeping Unit）(Sku)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
@Service("skuService")
public class SkuServiceImpl implements SkuService {
    @Resource
    private SkuMapper skuMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SkuNormalVO getNormalById(Long id) {
        return this.skuMapper.getNormalById(id);
    }

    /**
     * 新增数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    @Override
    public Sku insert(Sku sku) {
        this.skuMapper.insert(sku);
        return sku;
    }

    /**
     * 修改数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    @Override
    public SkuNormalVO updateById(Sku sku) {
        this.skuMapper.updateById(sku);
        return this.getNormalById(sku.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.skuMapper.deleteById(id) > 0;
    }
}
