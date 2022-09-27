package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.entity.CategoryAttributeTemplate;
import com.xiwang.csmall.product.pojo.vo.CategoryAttributeTemplateNormalVO;

/**
 * 类别与属性模版关联(CategoryAttributeTemplate)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
public interface CategoryAttributeTemplateService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CategoryAttributeTemplateNormalVO getNormalById(Long id);

    /**
     * 新增数据
     *
     * @param categoryAttributeTemplate 实例对象
     * @return 实例对象
     */
    CategoryAttributeTemplate insert(CategoryAttributeTemplate categoryAttributeTemplate);

    /**
     * 修改数据
     *
     * @param categoryAttributeTemplate 实例对象
     * @return 实例对象
     */
    CategoryAttributeTemplateNormalVO updateById(CategoryAttributeTemplate categoryAttributeTemplate);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
