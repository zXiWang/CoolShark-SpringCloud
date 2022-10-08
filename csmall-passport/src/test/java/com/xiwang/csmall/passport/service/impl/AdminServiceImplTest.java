package com.xiwang.csmall.passport.service.impl;

import com.xiwang.csmall.passport.pojo.dto.AdminAddNewDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    private AdminServiceImpl adminService;

    @Test
    void addNew() {
        AdminAddNewDTO obj= new AdminAddNewDTO();
        obj.setUsername("66");
        obj.setPhone("admin");
        obj.setEmail("admin");
        adminService.addNew(obj);
    }
}