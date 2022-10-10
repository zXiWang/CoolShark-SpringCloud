package com.xiwang.csmall.product.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xiwang.csmall.product.pojo.dto.AlbumAddNewDTO;
import com.xiwang.csmall.product.pojo.vo.AlbumListItemVO;
import com.xiwang.csmall.product.service.AlbumService;
import com.xiwang.csmall.product.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 相册(Album)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:35
 */
@Slf4j
@Api(tags = "01 相册管理")
@Validated
@RestController
@RequestMapping("/album")
public class AlbumController {
    /**
     * 服务对象
     */
    @Autowired
    private AlbumService albumService;

    @ApiOperation("添加相册")
    @ApiOperationSupport(order = 1)
    @PostMapping(value = "/addNew")
    public JsonResult<List<AlbumAddNewDTO>> addNew(@Validated AlbumAddNewDTO albumAddNewDTO) {
        log.debug("开始处理【添加相册】的请求，参数：{}", albumAddNewDTO);
        albumService.addNew(albumAddNewDTO);
        return JsonResult.ok();
    }

    //    @Deprecated
    @ApiOperation("删除相册")
    @ApiImplicitParam(name = "id", value = "相册id", required = true, dataType = "long")
    @ApiOperationSupport(order = 100)
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult delete(@PathVariable Long id) {
        log.debug("开始测试删除相册请求,id={}", id);
        albumService.delete(id);
        return JsonResult.ok();
    }

    @Deprecated
    @ApiOperation("删除相册2")
    @ApiImplicitParam(name = "id", value = "相册id", paramType = "query")
    @ApiOperationSupport(order = 101)
    @GetMapping("/delete/test")
    public JsonResult deleteTest(@Range(min = 1, message = "测试删除相册失败，id值必须是1或更大的有效整数！") Long id) {
        log.debug("开始测试删除相册请求,id={}", id);
        albumService.delete(id);
        return JsonResult.ok();
    }

    @ApiOperation("查询相册列表")
    @ApiOperationSupport(order = 400)
    @GetMapping(value = "/list")
    public JsonResult<List<AlbumListItemVO>> list() {
        List<AlbumListItemVO> list = albumService.list();
        return JsonResult.ok(list);
    }
}

