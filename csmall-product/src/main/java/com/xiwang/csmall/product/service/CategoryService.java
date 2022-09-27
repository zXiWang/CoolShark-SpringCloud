package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.dto.CategoryAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Category;
import com.xiwang.csmall.product.pojo.vo.CategoryNormalVO;

/**
 * 类别(Category)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
public interface CategoryService {


    void addNew(CategoryAddNewDTO categoryAddNewDTO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    void delete(Long id);

}
