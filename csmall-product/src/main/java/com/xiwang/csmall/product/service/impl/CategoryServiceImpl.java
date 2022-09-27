package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.mapper.CategoryMapper;
import com.xiwang.csmall.product.pojo.dto.CategoryAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Category;
import com.xiwang.csmall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.print.Pageable;

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


    @Override
    public void addNew(CategoryAddNewDTO categoryAddNewDTO) {
        if (categoryMapper.countByName(categoryAddNewDTO.getName())!= 0) {
            throw new ServiceException("添加类别失败!名称重复!");
        }
        Category category=new Category();
        category.setName(categoryAddNewDTO.getName());
        category.setParentId(categoryAddNewDTO.getParentId());
        category.setKeywords(categoryAddNewDTO.getKeywords());
        category.setSort(categoryAddNewDTO.getSort());
        category.setIcon(categoryAddNewDTO.getIcon());
        category.setEnable(categoryAddNewDTO.getEnable());
        category.setIsDisplay(categoryAddNewDTO.getIsDisplay());
        categoryMapper.insert(category);
    }

    @Override
    public void delete(Long id) {
        if(categoryMapper.getNormalById(id)==null) {
            throw new ServiceException("删除失败!不存在此类别!");
        }
        categoryMapper.delete(id);
    }
}
