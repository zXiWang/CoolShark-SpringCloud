package com.xiwang.csmall.passport.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xiwang.csmall.passport.pojo.dto.AdminAddNewDTO;
import com.xiwang.csmall.passport.service.AdminService;
import com.xiwang.csmall.passport.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@Api(tags = "01 管理员管理模块")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @ApiOperation("添加管理员")
    @ApiOperationSupport(order = 1)
    @PostMapping("/addNew")
    public JsonResult addNew(AdminAddNewDTO addNewDTO) {
        log.debug("开始处理添加管理员: {}", addNewDTO);
        adminService.addNew(addNewDTO);
        return JsonResult.ok();
    }

    @ApiOperation("删除管理员")
    @ApiOperationSupport(order = 100)
    @GetMapping("/delete")
    public JsonResult delete(Long id){
        log.debug("开始处理删除管理员: id={}", id);
        adminService.delete(id);
        return JsonResult.ok();
    }
}
