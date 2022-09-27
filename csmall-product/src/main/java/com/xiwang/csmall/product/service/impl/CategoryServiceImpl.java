package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.pojo.entity.Category;
import com.xiwang.csmall.product.mapper.CategoryMapper;
import com.xiwang.csmall.product.pojo.vo.CategoryNormalVO;
import com.xiwang.csmall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 类别(Category)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CategoryNormalVO getNormalById(Long id) {
        return this.categoryMapper.getNormalById(id);
    }

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category insert(Category category) {
        this.categoryMapper.insert(category);
        return category;
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public CategoryNormalVO updateById(Category category) {
        this.categoryMapper.updateById(category);
        return this.getNormalById(category.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.categoryMapper.deleteById(id) > 0;
    }
}
