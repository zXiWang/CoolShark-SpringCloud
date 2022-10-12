package com.xiwang.csmall.passport.service.impl;

import com.xiwang.csmall.passport.ex.ServiceException;
import com.xiwang.csmall.passport.mapper.AdminMapper;
import com.xiwang.csmall.passport.mapper.AdminRoleMapper;
import com.xiwang.csmall.passport.pojo.dto.AdminAddNewDTO;
import com.xiwang.csmall.passport.pojo.entity.Admin;
import com.xiwang.csmall.passport.pojo.entity.AdminRole;
import com.xiwang.csmall.passport.pojo.vo.AdminListItemVO;
import com.xiwang.csmall.passport.pojo.vo.AdminNormalVO;
import com.xiwang.csmall.passport.service.AdminService;
import com.xiwang.csmall.passport.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员(Admin)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-29 11:32:46
 */
@Slf4j
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Autowired
    AdminRoleMapper adminRoleMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<AdminListItemVO> list() {
        List<AdminListItemVO> list = adminMapper.list();
        list.remove(0);
        return list;
    }

    @Override
    public AdminNormalVO getNormalById(Long id) {
        return adminMapper.getNormalById(id);
    }

    @Override
    public void addNew(AdminAddNewDTO adminAddNewDTO) {
        Long[] roleIds = adminAddNewDTO.getRoleIds();
        log.debug("开始处理添加的角色是否合法.......");
        for (Long roleId : roleIds) {
            if (roleId == 1) {
                String message = "非法访问!";
                log.debug(message);
                throw new ServiceException(ServiceCode.ERR_INSERT, message);
            }
        }
        {
            String username = adminAddNewDTO.getUsername();
            int count = adminMapper.countByUsername(username);
            if (count != 0) {
                String message = "添加管理员失败!用户名 [" + username + "]已被占用";
                log.debug(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }
        {
            String phone = adminAddNewDTO.getPhone();
            int count = adminMapper.countByPhone(phone);
            if (count != 0) {
                String message = "添加管理员失败!号码 [" + phone + "]已被占用";
                log.debug(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }
        {
            String email = adminAddNewDTO.getEmail();
            int count = adminMapper.countByEmail(email);
            if (count != 0) {
                String message = "添加管理员失败!邮箱 [" + email + "]已被占用";
                log.debug(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminAddNewDTO, admin);
        // 从Admin对象中取出密码，进行加密处理，并将密文封装回Admin对象中
        String rawPassword = admin.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        admin.setPassword(encodedPassword);
        // 补全Admin对象中的属性值：loginCount >>> 0
        admin.setLoginCount(0l);
        log.debug("即将插入管理员数据:{}", admin);
        int rows = adminMapper.insert(admin);
        // 判断插入数据的结果是否符合预期
        if (rows != 1) {
            String message = "添加管理员失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }

        // 调用adminRoleMapper的insertBatch()方法插入关联数据

        List<AdminRole> adminRoleList = new ArrayList<>();
        for (Long roleId : roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(admin.getId());
            adminRole.setRoleId(roleId);
            adminRoleList.add(adminRole);
        }
        rows = adminRoleMapper.insertBatch(adminRoleList);
        if (rows != roleIds.length) {
            String message = "添加管理员失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【删除管理员】的业务，参数：{}", id);
        if (adminMapper.selectById(id) == null) {
            String message = "删除失败,管理员不存在!";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        // 判断参数id是否为1
        if (id == 1) {
            String message = "删除管理员失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        int rows = adminMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除管理员失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
        rows = adminRoleMapper.deleteByAdminId(id);
        if (rows < 1) {
            String message = "删除管理员失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    public void update(AdminAddNewDTO adminAddNewDTO) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminAddNewDTO, admin);
        adminMapper.update(admin);
    }

    @Override
    public void setEnabled(Long id) {
        UpdateEnableById(id, 1);

    }

    @Override
    public void setDisabled(Long id) {
        UpdateEnableById(id, 0);
    }

    private void UpdateEnableById(Long id, Integer enabled) {
        String[] tips = {"禁用", "启用"};
        log.debug("开始处理[{}管理员]的业务,参数{}", tips[enabled], id);
        AdminNormalVO result = adminMapper.getNormalById(id);
        if (result == null) {
            String message = "更新管理员失败!id= [" + id + "]的管理员不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_SELECT, message);
        }
        if (id == 1) {
            String message = "禁止修改系统管理员";
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
        Admin admin = new Admin();
        admin.setId(id);
        admin.setEnable(enabled);
        if (result.getEnable() == enabled) {
            String message = "设定的enabled相同,无需改变!";
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
        int rows = adminMapper.update(admin);
        if (rows != 1) {
            String message = "更新管理员失败!enable没有改变!";
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

}
