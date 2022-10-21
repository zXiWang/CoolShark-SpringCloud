package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.mapper.BrandCategoryMapper;
import com.xiwang.csmall.product.mapper.BrandMapper;
import com.xiwang.csmall.product.mapper.SpuMapper;
import com.xiwang.csmall.product.pojo.dto.BrandAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Brand;
import com.xiwang.csmall.product.pojo.vo.BrandListItemVO;
import com.xiwang.csmall.product.pojo.vo.BrandNormalVO;
import com.xiwang.csmall.product.repo.IBrandRedisRepository;
import com.xiwang.csmall.product.service.BrandService;
import com.xiwang.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 品牌(Brand)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
@Slf4j
@Service("brandService")
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandMapper brandMapper;
    @Autowired
    private BrandCategoryMapper brandCategoryMapper;
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    IBrandRedisRepository brandRedisRepository;

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
        int rows = brandMapper.insert(brand);
        if (rows != 1) {
            String message = "添加品牌失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {

        BrandNormalVO brandNormalVO = brandMapper.getNormalById(id);
        log.debug("开始处理【删除品牌】的业务，参数：{}", id);
        if (brandNormalVO == null) {
            String message = "删除失败!品牌不存在!";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        int rows = brandMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除品牌失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }

        // 检查此品牌是否关联了类别
        {
            int count = brandCategoryMapper.countByBrand(id);
            if (count > 0) {
                String message = "删除品牌失败！当前品牌仍关联了类别！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }

        // 检查此品牌是否关联了SPU
        {
            int count = spuMapper.countByBrand(id);
            if (count > 0) {
                String message = "删除品牌失败！当前品牌仍关联了商品！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }
    }

    @Override
    public void setEnabled(Long id) {
        UpdateEnableById(id, 1);

    }

    @Override
    public void setDisabled(Long id) {
        UpdateEnableById(id, 0);
    }

    private void UpdateEnableById(Long id, Integer enabled) {
        String[] tips = {"禁用", "启用"};
        log.debug("开始处理[{}品牌]的业务,参数{}", tips[enabled], id);
        BrandNormalVO result = brandMapper.getNormalById(id);
        if (result == null) {
            String message = "更新品牌失败!id= [" + id + "]的品牌不存在!";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_SELECT, message);
        }
        Brand brand = new Brand();
        brand.setId(id);
        brand.setEnable(enabled);
        if (result.getEnable() == enabled) {
            String message = "设定的enabled相同,无需改变!";
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
        int rows = brandMapper.updateById(brand);
        if (rows != 1) {
            String message = "更新品牌失败!enable没有改变!";
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public List<BrandListItemVO> list() {
//        return brandMapper.list();
        return brandRedisRepository.list();
    }

    @Override
    public BrandNormalVO getNormalById(Long id) {
        log.debug("开始处理【根据id查询品牌详情】的业务，参数：{}", id);
//        BrandNormalVO brand = brandMapper.getNormalById(id);
        BrandNormalVO brand = brandRedisRepository.get(id);
        if (brand == null) {
            String message = "获取品牌详情失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        return brand;
    }

    /**
     * 重建品牌数据缓存
     */
    @Override
    public void rebuildCache() {
        log.debug("删除Redis中原有的品牌数据");
        brandRedisRepository.deleteAll();

        log.debug("从MySQL中读取品牌列表");
        List<BrandListItemVO> brands = brandMapper.list();

        log.debug("将品牌列表写入到Redis");
        brandRedisRepository.save(brands);

        log.debug("逐一根据id从MySQL中读取品牌详情，并写入到Redis");
        for (BrandListItemVO item : brands) {
            BrandNormalVO brand = brandMapper.getNormalById(item.getId());
            brandRedisRepository.save(brand);
        }
    }

}
