package com.xiwang.csmall.product.repo;

import com.xiwang.csmall.product.pojo.vo.BrandListItemVO;
import com.xiwang.csmall.product.pojo.vo.BrandNormalVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class BrandRedisRepositoryTests {

    @Autowired
    IBrandRedisRepository repository;

    @Test
    void testSave() {
        BrandNormalVO brand = new BrandNormalVO();
        brand.setId(1L);
        brand.setName("华为");

        repository.save(brand);
        log.debug("向Redis中写入数据完成！");
    }

    @Test
    void testSaveList() {
        List<BrandListItemVO> brands = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            BrandListItemVO brand = new BrandListItemVO();
            brand.setId(i + 0L);
            brand.setName("测试品牌" + i);
            brands.add(brand);
        }

        repository.save(brands);
        log.debug("向Redis中写入列表数据完成！");
    }

    @Test
    void testGet() {
        Long id = 10000L;
        Object result = repository.get(id);
        log.debug("从Redis中读取【id={}】的数据，结果：{}", id, result);
    }

}
