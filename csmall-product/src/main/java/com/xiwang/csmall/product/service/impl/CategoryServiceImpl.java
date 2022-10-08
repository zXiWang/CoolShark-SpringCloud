package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.mapper.CategoryMapper;
import com.xiwang.csmall.product.pojo.dto.CategoryAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Category;
import com.xiwang.csmall.product.service.CategoryService;
import com.xiwang.csmall.product.web.ServiceCode;
import org.springframework.beans.BeanUtils;
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


    @Override
    public void addNew(CategoryAddNewDTO categoryAddNewDTO) {
        if (categoryMapper.countByName(categoryAddNewDTO.getName()) != 0) {
            String message = "添加类别失败!名称重复!";
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryAddNewDTO, category);
        categoryMapper.insert(category);
    }

    @Override
    public void delete(Long id) {
        if (categoryMapper.getNormalById(id) == null) {
            String message = "删除失败!不存在此类别!";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        categoryMapper.deleteById(id);
    }
}
