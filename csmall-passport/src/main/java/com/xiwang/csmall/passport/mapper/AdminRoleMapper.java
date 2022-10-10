package com.xiwang.csmall.passport.mapper;

import com.xiwang.csmall.passport.pojo.entity.Admin;
import com.xiwang.csmall.passport.pojo.entity.AdminRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理员角色关联(AdminRole)表数据库访问层
 *
 * @author 夕妄
 * @since 2022-10-09 16:43:54
 */
@Repository
public interface AdminRoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AdminRole queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param adminRole 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<AdminRole> queryAllByLimit(AdminRole adminRole, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param adminRole 查询条件
     * @return 总行数
     */
    long count(AdminRole adminRole);

    /**
     * 新增数据
     *
     * @param adminRole 实例对象
     * @return 影响行数
     */
    int insert(AdminRole adminRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdminRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AdminRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AdminRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AdminRole> entities);

    /**
     * 修改数据
     *
     * @param adminRole 实例对象
     * @return 影响行数
     */
    int update(AdminRole adminRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Transactional
    int deleteByAdminId(Long id);
    @Transactional
    int deleteByRoleId(Long id);

    int countByRoleId(Long id);

    Long[] getAdminIdByRoleId(Long id);
}

