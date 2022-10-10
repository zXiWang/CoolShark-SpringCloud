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
        AdminAddNewDTO obj = new AdminAddNewDTO();
        obj.setUsername("666");
        obj.setPhone("admin66");
        obj.setEmail("admin66");
        Long[] l = new Long[]{1L, 2L, 3L};
        obj.setRoleIds(l);
        adminService.addNew(obj);
    }

    @Test
    void list() {

    }

    @Test
    void testAddNew() {
    }

    @Test
    void update() {
        AdminAddNewDTO obj = new AdminAddNewDTO();
        obj.setUsername("666");
        obj.setPhone("admin66");
        obj.setEmail("admin66");

        adminService.update(obj);
    }
}