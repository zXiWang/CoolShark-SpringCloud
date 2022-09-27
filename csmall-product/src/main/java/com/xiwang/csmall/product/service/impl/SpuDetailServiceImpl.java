package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.pojo.entity.SpuDetail;
import com.xiwang.csmall.product.mapper.SpuDetailMapper;
import com.xiwang.csmall.product.pojo.vo.SpuDetailNormalVO;
import com.xiwang.csmall.product.service.SpuDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SPU详情(SpuDetail)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
@Service("spuDetailService")
public class SpuDetailServiceImpl implements SpuDetailService {
    @Resource
    private SpuDetailMapper spuDetailMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SpuDetailNormalVO getNormalById(Long id) {
        return this.spuDetailMapper.getNormalById(id);
    }


    /**
     * 新增数据
     *
     * @param spuDetail 实例对象
     * @return 实例对象
     */
    @Override
    public SpuDetail insert(SpuDetail spuDetail) {
        this.spuDetailMapper.insert(spuDetail);
        return spuDetail;
    }

    /**
     * 修改数据
     *
     * @param spuDetail 实例对象
     * @return 实例对象
     */
    @Override
    public SpuDetailNormalVO updateById(SpuDetail spuDetail) {
        this.spuDetailMapper.updateById(spuDetail);
        return this.getNormalById(spuDetail.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.spuDetailMapper.deleteById(id) > 0;
    }
}
