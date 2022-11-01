package cn.tedu.mall.front.service.impl;


import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.front.service.IFrontProductService;
import cn.tedu.mall.pojo.product.vo.*;
import cn.tedu.mall.product.service.front.IForFrontSpuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

@DubboService
@Service
@Slf4j
public class FrontProductServiceImpl implements IFrontProductService {

    @DubboReference
    private IForFrontSpuService dubboSpuService;


    @Override
    public JsonPage<SpuListItemVO> listSpuByCategoryId(Long categoryId, Integer page, Integer pageSize) {
        JsonPage<SpuListItemVO> spuList = dubboSpuService.listSpuByCategoryId(categoryId, page, pageSize);
        return spuList;
    }

    @Override
    public SpuStandardVO getFrontSpuById(Long id) {
        return null;
    }

    @Override
    public List<SkuStandardVO> getFrontSkusBySpuId(Long spuId) {
        return null;
    }

    @Override
    public SpuDetailStandardVO getSpuDetail(Long spuId) {
        return null;
    }

    @Override
    public List<AttributeStandardVO> getSpuAttributesBySpuId(Long spuId) {
        return null;
    }
}
