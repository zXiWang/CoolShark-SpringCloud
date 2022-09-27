package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.pojo.dto.AlbumAddNewDTO;
import com.xiwang.csmall.product.service.AlbumService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 相册(Album)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:35
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    /**
     * 服务对象
     */
    @Resource
    private AlbumService albumService;

    @RequestMapping("/addNew")
    public String addNew(AlbumAddNewDTO albumAddNewDTO) {
       try {
           albumService.addNew(albumAddNewDTO);
           return "相册添加成功!";
       }catch(RuntimeException e) {
           return "添加失败!名称重复!";
        }
    }

    
}

