package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.service.PictureService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 图片(Picture)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:40
 */
@RestController
@RequestMapping("/picture")
public class PictureController {
    /**
     * 服务对象
     */
    @Resource
    private PictureService pictureService;


    
}

