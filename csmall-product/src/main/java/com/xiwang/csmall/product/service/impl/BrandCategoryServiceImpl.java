package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.mapper.BrandCategoryMapper;
import com.xiwang.csmall.product.pojo.entity.BrandCategory;
import com.xiwang.csmall.product.pojo.vo.BrandCategoryNormalVO;
import com.xiwang.csmall.product.service.BrandCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 品牌与类别关联(BrandCategory)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
@Service("brandCategoryService")
public class BrandCategoryServiceImpl implements BrandCategoryService {
    @Resource
    private BrandCategoryMapper brandCategoryMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BrandCategoryNormalVO getNormalById(Long id) {
        return this.brandCategoryMapper.getNormalById(id);
    }


    /**
     * 新增数据
     *
     * @param brandCategory 实例对象
     * @return 实例对象
     */
    @Override
    public BrandCategory insert(BrandCategory brandCategory) {
        this.brandCategoryMapper.insert(brandCategory);
        return brandCategory;
    }

    /**
     * 修改数据
     *
     * @param brandCategory 实例对象
     * @return 实例对象
     */
    @Override
    public BrandCategoryNormalVO updateById(BrandCategory brandCategory) {
        this.brandCategoryMapper.updateById(brandCategory);
        return this.getNormalById(brandCategory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.brandCategoryMapper.deleteById(id) > 0;
    }
}
