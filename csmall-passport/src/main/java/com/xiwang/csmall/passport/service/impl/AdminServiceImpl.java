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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<AdminListItemVO> list(){
        return adminMapper.list();
    }

    @Override
    public AdminNormalVO getNormalById(Long id){
        return adminMapper.getNormalById(id);
    }

    @Transactional
    @Override
    public void addNew(AdminAddNewDTO adminAddNewDTO) {
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
        log.debug("即将插入管理员数据:{}", admin);
        adminMapper.insert(admin);

        // 调用adminRoleMapper的insertBatch()方法插入关联数据
        Long[] roleIds = adminAddNewDTO.getRoleIds();
        List<AdminRole> adminRoleList = new ArrayList<>();
        for (int i = 0; i < roleIds.length; i++) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(admin.getId());
            adminRole.setRoleId(roleIds[i]);
            adminRoleList.add(adminRole);
        }
        adminRoleMapper.insertBatch(adminRoleList);
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【删除管理员】的业务，参数：{}", id);

        if (adminMapper.selectById(id) == null) {
            String message ="删除失败,管理员不存在!";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        adminMapper.deleteById(id);
    }

}
