package com.xiwang.csmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xiwang.csmall.product.pojo.dto.AlbumAddNewDTO;
import com.xiwang.csmall.product.service.AlbumService;
import com.xiwang.csmall.product.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 相册(Album)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:35
 */
@Slf4j
@Api(tags = "01 相册管理")
@RestController
@RequestMapping("/album")
public class AlbumController {
    /**
     * 服务对象
     */
    @Resource
    private AlbumService albumService;

    @ApiOperation("添加相册")
    @ApiOperationSupport(order = 1)
    @PostMapping(value = "/addNew")
    public JsonResult addNew(AlbumAddNewDTO albumAddNewDTO) {
        log.debug("开始处理【添加相册】的请求，参数：{}", albumAddNewDTO);
        albumService.addNew(albumAddNewDTO);
        return JsonResult.ok();
    }

    @ApiOperation("删除相册")
    @ApiOperationSupport(order = 100)
    @GetMapping("/delete")
    public JsonResult delete(Long id) {
        log.debug("开始测试删除相册请求,id={}", id);
        albumService.delete(id);
        return JsonResult.ok();
    }


}

