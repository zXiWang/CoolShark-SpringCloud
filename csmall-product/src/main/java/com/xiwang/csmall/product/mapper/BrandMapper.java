package com.xiwang.csmall.product.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiwang.csmall.product.pojo.entity.Brand;
import com.xiwang.csmall.product.pojo.vo.BrandListItemVO;
import com.xiwang.csmall.product.pojo.vo.BrandNormalVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理相册数据的Mapper接口
 *
 * @author 夕妄
 * @version 1.0.0
 */

@Repository
public interface BrandMapper extends BaseMapper<Brand> {
    int insert(Brand brand);

    int insertBatch(List<Brand> brandList);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    int updateById(Brand brand);

    int count();

    BrandNormalVO getNormalById(Long id);

    List<BrandListItemVO> list();

    int countByName(String name);

}
