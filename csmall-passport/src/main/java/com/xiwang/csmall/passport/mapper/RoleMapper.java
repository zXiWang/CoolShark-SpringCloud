package com.xiwang.csmall.passport.mapper;

import com.xiwang.csmall.passport.pojo.entity.Role;
import com.xiwang.csmall.passport.pojo.vo.RoleListItemVO;
import com.xiwang.csmall.passport.pojo.vo.RoleNormalVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色(Role)表数据库访问层
 *
 * @author 夕妄
 * @since 2022-10-10 11:59:00
 */
 @Repository
public interface RoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RoleNormalVO getNormalById(Long id);

    List<RoleListItemVO> list();

    int countByName(String name);

    /**
     * 统计总行数
     *
     * @param role 查询条件
     * @return 总行数
     */
    long count(Role role);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int insert(Role role);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Role> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Role> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Role> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Role> entities);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

