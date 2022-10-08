package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.service.SpuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * SPU（Standard Product Unit）(Spu)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:41
 */
@RestController
@RequestMapping("/spu")
public class SpuController {
    /**
     * 服务对象
     */
    @Resource
    private SpuService spuService;


}

