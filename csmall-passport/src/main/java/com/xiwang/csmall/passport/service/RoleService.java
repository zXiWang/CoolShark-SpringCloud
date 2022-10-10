package com.xiwang.csmall.passport.service;

import com.xiwang.csmall.passport.pojo.dto.RoleAddNewDTO;
import com.xiwang.csmall.passport.pojo.entity.Role;
import com.xiwang.csmall.passport.pojo.vo.RoleListItemVO;
import com.xiwang.csmall.passport.pojo.vo.RoleNormalVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色(Role)表服务接口
 *
 * @author 夕妄
 * @since 2022-10-10 14:10:07
 */
public interface RoleService {


    /**
     * 查询列表
     * @return 实例对象
     */
    List<RoleListItemVO> list();

    /**
     * 新增数据
     *
     * @param roleAddNewDTO 实例对象
     */
    @Transactional
    void addNew(RoleAddNewDTO roleAddNewDTO);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Transactional
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    @Transactional
    void delete(Long id);

    RoleNormalVO getNormalById(Long id);
}
