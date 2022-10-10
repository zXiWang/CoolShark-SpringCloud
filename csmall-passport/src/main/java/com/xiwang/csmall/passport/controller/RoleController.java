package com.xiwang.csmall.passport.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xiwang.csmall.passport.pojo.dto.RoleAddNewDTO;
import com.xiwang.csmall.passport.pojo.vo.RoleListItemVO;
import com.xiwang.csmall.passport.pojo.vo.RoleNormalVO;
import com.xiwang.csmall.passport.service.RoleService;
import com.xiwang.csmall.passport.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Api(tags = "02 角色模块")
@Validated
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @ApiOperation("添加角色")
    @ApiOperationSupport(order = 1)
    @PostMapping("/addNew")
    public JsonResult addNew(RoleAddNewDTO addNewDTO) {
        log.debug("开始处理添加角色: {}", addNewDTO);
        roleService.addNew(addNewDTO);
        return JsonResult.ok();
    }

    @ApiOperation("删除角色")
    @ApiOperationSupport(order = 100)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id) {
        log.debug("开始处理删除角色: id={}", id);
        roleService.delete(id);
        return JsonResult.ok();
    }

    @ApiOperation("查询角色列表")
    @ApiOperationSupport(order = 400)
    @GetMapping(value = "/list")
    public JsonResult<List<RoleListItemVO>> list() {
        List<RoleListItemVO> list = roleService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("查询角色详情")
    @ApiOperationSupport(order = 401)
    @GetMapping(value = "/details")
    public JsonResult<RoleNormalVO> details(Long id) {
        log.debug("输入了id="+id);
        RoleNormalVO object = roleService.getNormalById(id);
        return JsonResult.ok(object);
    }
}
