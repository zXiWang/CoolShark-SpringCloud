package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.entity.BrandCategory;
import com.xiwang.csmall.product.pojo.vo.BrandCategoryNormalVO;

/**
 * 品牌与类别关联(BrandCategory)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
public interface BrandCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BrandCategoryNormalVO getNormalById(Long id);



    /**
     * 新增数据
     *
     * @param brandCategory 实例对象
     * @return 实例对象
     */
    BrandCategory insert(BrandCategory brandCategory);

    /**
     * 修改数据
     *
     * @param brandCategory 实例对象
     * @return 实例对象
     */
    BrandCategoryNormalVO updateById(BrandCategory brandCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
