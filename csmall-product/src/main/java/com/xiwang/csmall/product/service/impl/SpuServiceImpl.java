package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.mapper.SpuMapper;
import com.xiwang.csmall.product.pojo.entity.Spu;
import com.xiwang.csmall.product.pojo.vo.SpuNormalVO;
import com.xiwang.csmall.product.service.SpuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SPU（Standard Product Unit）(Spu)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
@Service("spuService")
public class SpuServiceImpl implements SpuService {
    @Resource
    private SpuMapper spuMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SpuNormalVO getNormalById(Long id) {
        return this.spuMapper.getNormalById(id);
    }



    /**
     * 新增数据
     *
     * @param spu 实例对象
     * @return 实例对象
     */
    @Override
    public Spu insert(Spu spu) {
        this.spuMapper.insert(spu);
        return spu;
    }

    /**
     * 修改数据
     *
     * @param spu 实例对象
     * @return 实例对象
     */
    @Override
    public SpuNormalVO updateById(Spu spu) {
        this.spuMapper.updateById(spu);
        return this.getNormalById(spu.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.spuMapper.deleteById(id) > 0;
    }
}
