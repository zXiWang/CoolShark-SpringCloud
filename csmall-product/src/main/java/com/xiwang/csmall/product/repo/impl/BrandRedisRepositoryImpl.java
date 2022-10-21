package com.xiwang.csmall.product.repo.impl;

import com.xiwang.csmall.product.pojo.vo.BrandListItemVO;
import com.xiwang.csmall.product.pojo.vo.BrandNormalVO;
import com.xiwang.csmall.product.repo.IBrandRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 处理品牌缓存的数据访问实现类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Repository
public class BrandRedisRepositoryImpl implements IBrandRedisRepository {

    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    public BrandRedisRepositoryImpl() {
        log.debug("创建处理缓存的数据访问对象：BrandRedisRepositoryImpl");
    }

    @Override
    public void save(BrandNormalVO brandNormalVO) {
        log.debug("准备向Redis中写入数据：{}", brandNormalVO);
        String key = getItemKey(brandNormalVO.getId());
        redisTemplate.opsForSet().add(getAllKeysKey(), key);
        redisTemplate.opsForValue().set(key, brandNormalVO);
    }

    @Override
    public void save(List<BrandListItemVO> brands) {
        String key = getListKey();
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        redisTemplate.opsForSet().add(getAllKeysKey(), key);
        for (BrandListItemVO brand : brands) {
            ops.rightPush(key, brand);
        }
    }

    @Override
    public BrandNormalVO get(Long id) {
        String key = getItemKey(id);
        Serializable serializable = redisTemplate.opsForValue().get(key);
        if (serializable != null) {
            if (serializable instanceof BrandNormalVO) {
                return (BrandNormalVO) serializable;
            }
        }
        return null;
    }

    @Override
    public List<BrandListItemVO> list() {
        long start = 0;
        long end = -1;
        return list(start, end);
    }

    @Override
    public List<BrandListItemVO> list(long start, long end) {
        String key = getListKey();
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        List<Serializable> list = ops.range(key, start, end);
        List<BrandListItemVO> brands = new ArrayList<>();
        for (Serializable item : list) {
            brands.add((BrandListItemVO) item);
        }
        return brands;
    }

    @Override
    public Long deleteAll() {
        Set<String> allKeys = getAllKeys();
        return redisTemplate.delete(allKeys);
    }

    private String getItemKey(Long id) {
        return BRAND_ITEM_KEY_PREFIX + id;
    }

    private String getListKey() {
        return BRAND_LIST_KEY;
    }

    private String getAllKeysKey() {
        return BRAND_ALL_KEYS_KEY;
    }

    private Set<String> getAllKeys() {
        // return redisTemplate.keys(BRAND_KEY_PREFIX + "*");
        Set<Serializable> members = redisTemplate.opsForSet().members(getAllKeysKey());
        Set<String> keys = new HashSet<>();
        for (Serializable member : members) {
            keys.add((String) member);
        }
        return keys;
    }

}
