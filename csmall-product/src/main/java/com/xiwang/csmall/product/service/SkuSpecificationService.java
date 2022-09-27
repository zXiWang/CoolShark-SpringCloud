package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.entity.SkuSpecification;
import com.xiwang.csmall.product.pojo.vo.SkuSpecificationNormalVO;

/**
 * SKU数据(SkuSpecification)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
public interface SkuSpecificationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkuSpecificationNormalVO getNormalById(Long id);

    /**
     * 新增数据
     *
     * @param skuSpecification 实例对象
     * @return 实例对象
     */
    SkuSpecification insert(SkuSpecification skuSpecification);

    /**
     * 修改数据
     *
     * @param skuSpecification 实例对象
     * @return 实例对象
     */
    SkuSpecificationNormalVO updateById(SkuSpecification skuSpecification);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
