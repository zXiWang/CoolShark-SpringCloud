package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.dto.BrandAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Brand;
import com.xiwang.csmall.product.pojo.vo.BrandNormalVO;

/**
 * 品牌(Brand)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
public interface BrandService {


    void addNew(BrandAddNewDTO brandAddNewDTO);
}
