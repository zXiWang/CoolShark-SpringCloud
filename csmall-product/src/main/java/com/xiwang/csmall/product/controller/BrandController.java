package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.pojo.dto.BrandAddNewDTO;
import com.xiwang.csmall.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 品牌(Brand)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:37
 */
@Slf4j
@RestController
@RequestMapping("/brand")
public class BrandController {
    /**
     * 服务对象
     */
    @Resource
    private BrandService brandService;

    /**
     * 添加品牌
     *
     * @param brandAddNewDTO 实例对象
     * @return 返回信息
     */
    @PostMapping("/addNew")
    public String addNew(BrandAddNewDTO brandAddNewDTO) {
        log.debug("品牌添加开始对象实例为: {}", brandAddNewDTO);
        brandService.addNew(brandAddNewDTO);
        return "添加成功!";
    }

    @RequestMapping
    public String delete(Long id) {
        log.debug("品牌删除开始id= {}",id);
        brandService.delete(id);
        return "删除成功!";
    }
}



