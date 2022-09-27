package com.xiwang.csmall.product.controller;

import com.xiwang.csmall.product.pojo.dto.BrandAddNewDTO;
import com.xiwang.csmall.product.service.BrandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 品牌(Brand)表控制层
 *
 * @author 夕妄
 * @since 2022-09-26 17:20:37
 */
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
     * @param brandAddNewDTO 实例对象
     * @return 返回信息
     */
    @RequestMapping("/addNew")
    public String addNew(BrandAddNewDTO brandAddNewDTO) {
        try{
            brandService.addNew(brandAddNewDTO);
            return "添加成功!";
        }catch (Exception e){
            return "添加失败!名称重复!";
        }
    }

    
}

