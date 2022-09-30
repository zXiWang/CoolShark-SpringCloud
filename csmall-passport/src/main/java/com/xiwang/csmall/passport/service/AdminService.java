package com.xiwang.csmall.passport.service;

import com.xiwang.csmall.passport.pojo.dto.AdminAddNewDTO;

/**
 * 管理员(Admin)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-29 11:32:46
 */
public interface AdminService {
    void addNew(AdminAddNewDTO adminAddNewDTO);

    void delete(Long id);

}
