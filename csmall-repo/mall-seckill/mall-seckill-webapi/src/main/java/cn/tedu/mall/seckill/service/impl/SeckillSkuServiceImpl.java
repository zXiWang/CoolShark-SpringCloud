package cn.tedu.mall.seckill.service.impl;


import cn.tedu.mall.pojo.product.vo.SkuStandardVO;
import cn.tedu.mall.pojo.seckill.model.SeckillSku;
import cn.tedu.mall.pojo.seckill.vo.SeckillSkuVO;
import cn.tedu.mall.product.service.seckill.IForSeckillSkuService;
import cn.tedu.mall.seckill.mapper.SeckillSkuMapper;
import cn.tedu.mall.seckill.service.ISeckillSkuService;
import cn.tedu.mall.seckill.utils.SeckillCacheUtils;
import com.alibaba.nacos.client.naming.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SeckillSkuServiceImpl implements ISeckillSkuService {

    @Autowired
    private SeckillSkuMapper skuMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @DubboReference
    private IForSeckillSkuService dubboSkuService;

    @Override
    public List<SeckillSkuVO> listSeckillSkus(Long spuId) {
        // 执行查询,根据spuId查询秒杀的sku列表
        List<SeckillSku> seckillSkus = skuMapper.findSeckillSkusBySpuId(spuId);
        List<SeckillSkuVO> seckillSkuVOs = new ArrayList<SeckillSkuVO>();
        for (SeckillSku sku : seckillSkus) {
            // 先获取skuId,方便后面调用
            Long skuId = sku.getSkuId();
            String seckillSkuVOKey = SeckillCacheUtils.getSeckillSkuVOKey(skuId);
            SeckillSkuVO seckillSkuVO = null;
            if (redisTemplate.hasKey(seckillSkuVOKey)) {
                seckillSkuVO = (SeckillSkuVO) redisTemplate
                        .boundValueOps(seckillSkuVOKey).get();
            } else {
                SkuStandardVO skuStandardVO = dubboSkuService.getById(spuId);
                seckillSkuVO = new SeckillSkuVO();
                // 将常规信息的同名属性赋值到seckillSkuVO对象中
                BeanUtils.copyProperties(skuStandardVO, seckillSkuVO);
                // 秒杀信息手动赋值
                seckillSkuVO.setSeckillPrice(sku.getSeckillPrice());
                seckillSkuVO.setStock(sku.getSeckillStock());
                seckillSkuVO.setSeckillLimit(sku.getSeckillLimit());
                // seckillSkuVO所有属性保存在Redis中
                redisTemplate.boundValueOps(seckillSkuVOKey)
                        .set(seckillSkuVO, 10 * 60 * 1000 + RandomUtils.nextInt(10000),
                                TimeUnit.MILLISECONDS);
            }
            // 运行到这里,无论Redis中有没有这个key,这个seckillSkuVO对象都被赋值了
            seckillSkuVOs.add(seckillSkuVO);
        }
        return seckillSkuVOs;
    }
}
