package com.xiwang.csmall.product.mapper;

import com.xiwang.csmall.product.pojo.entity.SkuSpecification;
import com.xiwang.csmall.product.pojo.vo.SkuSpecificationNormalVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * SKU数据(SkuSpecification)表数据库访问层
 *
 * @author 夕妄
 * @since 2022-09-26 09:20:51
 */
public interface SkuSpecificationMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkuSpecificationNormalVO getNormalById(Long id);

    /**
     * 查询指定行数据
     *
     * @param SkuSpecification 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SkuSpecification> list(SkuSpecification SkuSpecification, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    int count();

    /**
     * 新增数据
     *
     * @param SkuSpecification 实例对象
     * @return 影响行数
     */
    int insert(SkuSpecification SkuSpecification);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SkuSpecification> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SkuSpecification> entities);


    /**
     * 修改数据
     *
     * @param SkuSpecification 实例对象
     * @return 影响行数
     */
    int updateById(SkuSpecification SkuSpecification);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

