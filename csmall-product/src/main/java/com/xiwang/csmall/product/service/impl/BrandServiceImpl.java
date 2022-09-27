package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.mapper.BrandMapper;
import com.xiwang.csmall.product.pojo.dto.BrandAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Brand;
import com.xiwang.csmall.product.service.BrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 品牌(Brand)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
@Service("brandService")
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandMapper brandMapper;

    /**
     * 添加品牌
     * @param albumAddNewDTO 实例对象
     */
    public void addNew(BrandAddNewDTO albumAddNewDTO){
        if(brandMapper.countByName(albumAddNewDTO.getName())!=0){
            throw new RuntimeException();
        }
        Brand brand= new Brand();
        brand.setName(albumAddNewDTO.getName());
        brand.setPinyin(albumAddNewDTO.getPinyin());
        brand.setLogo(albumAddNewDTO.getLogo());
        brand.setDescription(albumAddNewDTO.getDescription());
        brand.setKeywords(albumAddNewDTO.getKeywords());
        brand.setSort(albumAddNewDTO.getSort());
        brand.setEnable(albumAddNewDTO.getEnable());
        brandMapper.insert(brand);
    }

    public void delete(Long id){
        if(brandMapper.getNormalById(id)==null){
            throw new ServiceException("删除失败!没有该品牌");
        }
        brandMapper.deleteById(id);
    }
}
