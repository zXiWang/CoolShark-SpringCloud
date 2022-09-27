package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.entity.SpuDetail;
import com.xiwang.csmall.product.pojo.vo.SpuDetailNormalVO;

/**
 * SPU详情(SpuDetail)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
public interface SpuDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SpuDetailNormalVO getNormalById(Long id);

    /**
     * 新增数据
     *
     * @param spuDetail 实例对象
     * @return 实例对象
     */
    SpuDetail insert(SpuDetail spuDetail);

    /**
     * 修改数据
     *
     * @param spuDetail 实例对象
     * @return 实例对象
     */
    SpuDetailNormalVO updateById(SpuDetail spuDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
