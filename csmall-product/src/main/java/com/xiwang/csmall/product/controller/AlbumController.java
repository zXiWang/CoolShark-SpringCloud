package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.pojo.dto.AlbumAddNewDTO;
import com.xiwang.csmall.product.service.AlbumService;
import lombok.extern.slf4j.Slf4j;
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
@RestController
@RequestMapping("/album")
public class AlbumController {
    /**
     * 服务对象
     */
    @Resource
    private AlbumService albumService;

    @PostMapping(value = "/addNew")
    public String addNew(AlbumAddNewDTO albumAddNewDTO) {
        albumService.addNew(albumAddNewDTO);
        return "相册添加成功!";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
      log.debug("开始测试删除相册请求,id={}",id);
      albumService.delete(id);
        return "相册删除成功!";
    }


}

