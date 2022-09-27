package com.xiwang.csmall.product.mapper;

import com.xiwang.csmall.product.pojo.entity.Category;
import com.xiwang.csmall.product.pojo.vo.CategoryNormalVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 类别(Category)表数据库访问层
 *
 * @author 夕妄
 * @since 2022-09-26 09:20:51
 */
public interface CategoryMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CategoryNormalVO getNormalById(Long id);

    /**
     * 查询指定行数据
     *
     * @param Category 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Category> list(Category Category, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    int count();

    /**
     * 新增数据
     *
     * @param Category 实例对象
     * @return 影响行数
     */
    int insert(Category Category);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Category> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Category> entities);


    /**
     * 修改数据
     *
     * @param Category 实例对象
     * @return 影响行数
     */
    int updateById(Category Category);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

