package com.xiwang.csmall.passport.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xiwang.csmall.passport.pojo.dto.AdminAddNewDTO;
import com.xiwang.csmall.passport.pojo.dto.AdminLoginDTO;
import com.xiwang.csmall.passport.pojo.vo.AdminListItemVO;
import com.xiwang.csmall.passport.pojo.vo.AdminNormalVO;
import com.xiwang.csmall.passport.secrity.LoginPrincipal;
import com.xiwang.csmall.passport.service.AdminService;
import com.xiwang.csmall.passport.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Api(tags = "01 管理员模块")
@Validated
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @ApiOperation("添加管理员")
    @ApiOperationSupport(order = 100)
    @PostMapping("/addNew")
    public JsonResult addNew(AdminAddNewDTO addNewDTO) {
        log.debug("开始处理添加管理员: {}", addNewDTO);
        adminService.addNew(addNewDTO);
        return JsonResult.ok();
    }

    @ApiOperation("删除管理员")
    @ApiOperationSupport(order = 200)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id,
                                   @ApiIgnore @AuthenticationPrincipal LoginPrincipal loginPrincipal) {
        log.debug("开始处理【删除管理员】的请求，参数：{}", id);
        log.debug("当前登录的当事人：{}", loginPrincipal);
        adminService.delete(id);
        return JsonResult.ok();
    }

    @ApiOperation("启用管理员")
    @ApiOperationSupport(order = 301)
    @PostMapping("/{id:[0-9]+}/enable")
    public JsonResult enable(@PathVariable Long id) {
        log.debug("开始处理修改管理员: id={}", id);
        adminService.setEnabled(id);
        return JsonResult.ok();
    }

    @ApiOperation("禁用管理员")
    @ApiOperationSupport(order = 302)
    @PostMapping("/{id:[0-9]+}/disable")
    public JsonResult disable(@PathVariable Long id) {
        log.debug("开始处理修改管理员: id={}", id);
        adminService.setDisabled(id);
        return JsonResult.ok();
    }

    @ApiOperation("查询管理员列表")
    @ApiOperationSupport(order = 400)
    @GetMapping(value = "/list")
    public JsonResult<List<AdminListItemVO>> list() {
        List<AdminListItemVO> list = adminService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("查询管理员详情")
    @ApiOperationSupport(order = 401)
    @GetMapping(value = "/details")
    public JsonResult<AdminNormalVO> details(Long id) {
        log.debug("输入了id=" + id);
        AdminNormalVO object = adminService.getNormalById(id);
        return JsonResult.ok(object);
    }

    @ApiOperation("登录管理员")
    @ApiOperationSupport(order = 402)
    @PostMapping("/login")
    public JsonResult<String> login(AdminLoginDTO adminLoginDTO) {
        log.debug("开始处理登录,参数:{}", adminLoginDTO);
        String jwt = adminService.login(adminLoginDTO);
        return JsonResult.ok(jwt);
    }
}
