package com.xiwang.csmall.passport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiwang.csmall.passport.pojo.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    int insert(Admin admin);

    int countByUsername(String username);

    int countByPhone(String phone);

    int countByEmail(String email);
}
