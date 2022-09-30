package com.xiwang.csmall.passport.service.impl;

import com.xiwang.csmall.passport.ex.ServiceException;
import com.xiwang.csmall.passport.mapper.AdminMapper;
import com.xiwang.csmall.passport.pojo.dto.AdminAddNewDTO;
import com.xiwang.csmall.passport.pojo.entity.Admin;
import com.xiwang.csmall.passport.service.AdminService;
import com.xiwang.csmall.passport.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    }

    @Override
    public void delete(Long id) {
        if (adminMapper.selectById(id) == null) {
            String message ="删除失败,管理员不存在!";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        adminMapper.deleteById(id);
    }

}
