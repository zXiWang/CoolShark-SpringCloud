package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.entity.Category;
import com.xiwang.csmall.product.pojo.vo.CategoryNormalVO;

/**
 * 类别(Category)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
public interface CategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CategoryNormalVO getNormalById(Long id);

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category insert(Category category);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    CategoryNormalVO updateById(Category category);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
