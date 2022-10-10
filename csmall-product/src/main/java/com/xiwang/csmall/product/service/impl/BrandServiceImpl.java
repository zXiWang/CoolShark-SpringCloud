package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.mapper.BrandMapper;
import com.xiwang.csmall.product.pojo.dto.BrandAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Brand;
import com.xiwang.csmall.product.pojo.vo.BrandListItemVO;
import com.xiwang.csmall.product.service.BrandService;
import com.xiwang.csmall.product.web.ServiceCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
     *
     * @param brandAddNewDTO 实例对象
     */
    @Override
    public void addNew(BrandAddNewDTO brandAddNewDTO) {
        if (brandMapper.countByName(brandAddNewDTO.getName()) != 0) {
            String message = "添加品牌失败!存在相同名称";
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        Brand brand = new Brand();
        BeanUtils.copyProperties(brandAddNewDTO, brand);
        brandMapper.insert(brand);
    }

    @Override
    public void delete(Long id) {
        if (brandMapper.getNormalById(id) == null) {
            String message = "删除失败!没有该品牌";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        brandMapper.deleteById(id);
    }

    @Override
    public List<BrandListItemVO> list() {
        return brandMapper.list();
    }
}
