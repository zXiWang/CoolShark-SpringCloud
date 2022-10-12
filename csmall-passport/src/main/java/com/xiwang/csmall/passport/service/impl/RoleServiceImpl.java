package com.xiwang.csmall.passport.service.impl;

import com.xiwang.csmall.passport.ex.ServiceException;
import com.xiwang.csmall.passport.mapper.AdminMapper;
import com.xiwang.csmall.passport.mapper.AdminRoleMapper;
import com.xiwang.csmall.passport.mapper.RoleMapper;
import com.xiwang.csmall.passport.pojo.dto.RoleAddNewDTO;
import com.xiwang.csmall.passport.pojo.entity.Role;
import com.xiwang.csmall.passport.pojo.vo.RoleListItemVO;
import com.xiwang.csmall.passport.pojo.vo.RoleNormalVO;
import com.xiwang.csmall.passport.service.RoleService;
import com.xiwang.csmall.passport.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色(Role)表服务实现类
 *
 * @author 夕妄
 * @since 2022-10-10 14:10:02
 */
@Slf4j
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private AdminRoleMapper adminRoleMapper;

    @Override
    public List<RoleListItemVO> list() {
        List<RoleListItemVO> list = roleMapper.list();
        list.remove(0);
        return list;
    }

    @Override
    public void addNew(RoleAddNewDTO roleAddNewDTO) {
        {
            String name = roleAddNewDTO.getName();
            int count = roleMapper.countByName(name);
            if (count != 0) {
                String message = "添加角色失败!名称 [" + name + "]已被占用";
                log.debug(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleAddNewDTO, role);
        log.debug("即将插入角色数据:{}", role);
        int rows = roleMapper.insert(role);
        // 判断插入数据的结果是否符合预期
        if (rows != 1) {
            String message = "添加角色失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【删除角色】的业务，参数：{}", id);
        // 判断参数id是否为1
        if (id == 1) {
            String message = "删除角色失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        if (roleMapper.getNormalById(id) == null) {
            String message = "删除失败,角色不存在!";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        int rows = roleMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除角色失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
        rows = adminRoleMapper.countByRoleId(id);
        if (rows >= 1) {
            rows = adminMapper.deleteByIds(adminRoleMapper.getAdminIdByRoleId(id));
            if (rows < 1) {
                String message = "删除角色失败，服务器忙，请稍后再次尝试！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_DELETE, message);
            }
            rows = adminRoleMapper.deleteByRoleId(id);
            if (rows < 1) {
                String message = "删除角色失败，服务器忙，请稍后再次尝试！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_DELETE, message);
            }
        }


    }

    @Override
    public RoleNormalVO getNormalById(Long id) {
        return roleMapper.getNormalById(id);
    }

}
