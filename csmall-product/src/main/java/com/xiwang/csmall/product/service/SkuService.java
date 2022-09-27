package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.entity.Sku;
import com.xiwang.csmall.product.pojo.vo.SkuNormalVO;

/**
 * SKU（Stock Keeping Unit）(Sku)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
public interface SkuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkuNormalVO getNormalById(Long id);


    /**
     * 新增数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    Sku insert(Sku sku);

    /**
     * 修改数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    SkuNormalVO updateById(Sku sku);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
