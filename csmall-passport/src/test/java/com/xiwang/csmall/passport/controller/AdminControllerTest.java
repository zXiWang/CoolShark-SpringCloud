package com.xiwang.csmall.passport.controller;

import com.xiwang.csmall.passport.pojo.vo.AdminListItemVO;
import com.xiwang.csmall.passport.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminControllerTest {

    @Autowired
    private AdminService adminService;

    @Test
    void list() {
        List<AdminListItemVO> list=adminService.list();
        System.out.println(list);
    }
}