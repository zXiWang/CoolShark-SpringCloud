package com.xiwang.csmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiwang.csmall.product.pojo.entity.Attribute;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

/**
 * 属性(PmsAttribute)表数据库访问层
 *
 * @author makejava
 * @since 2022-09-24 10:52:47
 */
@Repository
public interface AttributeMapper extends BaseMapper<Attribute> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Attribute queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param attribute 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<Attribute> queryAllByLimit(Attribute attribute, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param attribute 查询条件
     * @return 总行数
     */
    long count(Attribute attribute);

    /**
     * 新增数据
     *
     * @param attribute 实例对象
     * @return 影响行数
     */
    int insert(Attribute attribute);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Attribute> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Attribute> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Attribute> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Attribute> entities);

    /**
     * 修改数据
     *
     * @param attribute 实例对象
     * @return 影响行数
     */
    int update(Attribute attribute);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过主键批量删除数据
     *
     * @param ids 主键数组
     * @return 影响行数
     */
    int deleteByIdS(Long[] ids);

}

