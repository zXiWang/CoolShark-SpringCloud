package com.xiwang.csmall.passport.mapper;

import com.xiwang.csmall.passport.pojo.entity.AdminRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class AdminRoleMapperTest {

    @Autowired
    private AdminRoleMapper mapper;
    @Test
    void insertBatch() {
        List<AdminRole> roles = new ArrayList<AdminRole>();
        for (int i = 0; i < 10; i++) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId((long) i);
            adminRole.setRoleId((long) i);
            roles.add(adminRole);
        }
        mapper.insertBatch(roles);

    }
}