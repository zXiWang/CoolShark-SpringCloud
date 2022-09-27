package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.pojo.entity.CategoryAttributeTemplate;
import com.xiwang.csmall.product.mapper.CategoryAttributeTemplateMapper;
import com.xiwang.csmall.product.pojo.vo.CategoryAttributeTemplateNormalVO;
import com.xiwang.csmall.product.service.CategoryAttributeTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 类别与属性模版关联(CategoryAttributeTemplate)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
@Service("categoryAttributeTemplateService")
public class CategoryAttributeTemplateServiceImpl implements CategoryAttributeTemplateService {
    @Resource
    private CategoryAttributeTemplateMapper categoryAttributeTemplateMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CategoryAttributeTemplateNormalVO getNormalById(Long id) {
        return this.categoryAttributeTemplateMapper.getNormalById(id);
    }

    /**
     * 新增数据
     *
     * @param categoryAttributeTemplate 实例对象
     * @return 实例对象
     */
    @Override
    public CategoryAttributeTemplate insert(CategoryAttributeTemplate categoryAttributeTemplate) {
        this.categoryAttributeTemplateMapper.insert(categoryAttributeTemplate);
        return categoryAttributeTemplate;
    }

    /**
     * 修改数据
     *
     * @param categoryAttributeTemplate 实例对象
     * @return 实例对象
     */
    @Override
    public CategoryAttributeTemplateNormalVO updateById(CategoryAttributeTemplate categoryAttributeTemplate) {
        this.categoryAttributeTemplateMapper.updateById(categoryAttributeTemplate);
        return this.getNormalById(categoryAttributeTemplate.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.categoryAttributeTemplateMapper.deleteById(id) > 0;
    }
}
