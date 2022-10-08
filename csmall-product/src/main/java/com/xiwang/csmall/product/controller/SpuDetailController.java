package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.service.SpuDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * SPU详情(SpuDetail)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:42
 */
@RestController
@RequestMapping("/spuDetail")
public class SpuDetailController {
    /**
     * 服务对象
     */
    @Resource
    private SpuDetailService spuDetailService;


}

