package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.entity.Spu;
import com.xiwang.csmall.product.pojo.vo.SpuNormalVO;

/**
 * SPU（Standard Product Unit）(Spu)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
public interface SpuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SpuNormalVO getNormalById(Long id);

    /**
     * 新增数据
     *
     * @param spu 实例对象
     * @return 实例对象
     */
    Spu insert(Spu spu);

    /**
     * 修改数据
     *
     * @param spu 实例对象
     * @return 实例对象
     */
    SpuNormalVO updateById(Spu spu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
