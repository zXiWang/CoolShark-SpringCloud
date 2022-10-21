package com.xiwang.csmall.product.preload;

import com.xiwang.csmall.product.mapper.BrandMapper;
import com.xiwang.csmall.product.pojo.vo.BrandListItemVO;
import com.xiwang.csmall.product.pojo.vo.BrandNormalVO;
import com.xiwang.csmall.product.repo.IBrandRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 处理缓存预热的类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
//@Component
public class CachePreload implements ApplicationRunner {

    @Autowired
    BrandMapper brandMapper;
    @Autowired
    IBrandRedisRepository brandRedisRepository;

    public CachePreload() {
        log.debug("创建ApplicationRunner接口的实现对象：CachePreload");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("开始处理缓存预热");

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

        log.debug("缓存预热完成");
    }

}
