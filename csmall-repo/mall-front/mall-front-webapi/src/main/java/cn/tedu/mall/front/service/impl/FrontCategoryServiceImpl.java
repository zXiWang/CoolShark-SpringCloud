package cn.tedu.mall.front.service.impl;


import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.front.service.IFrontCategoryService;
import cn.tedu.mall.pojo.front.entity.FrontCategoryEntity;
import cn.tedu.mall.pojo.front.vo.FrontCategoryTreeVO;
import cn.tedu.mall.pojo.product.vo.CategoryStandardVO;
import cn.tedu.mall.product.service.front.IForFrontCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@DubboService
@Service
@Slf4j
public class FrontCategoryServiceImpl implements IFrontCategoryService {

    @DubboReference
    private IForFrontCategoryService dubboCategoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String CATEGORY_TREE_KEY = "category_tree";

    @Override
    public FrontCategoryTreeVO categoryTree() {
        if (redisTemplate.hasKey(CATEGORY_TREE_KEY)) {
            FrontCategoryTreeVO<FrontCategoryEntity> treeVO =
                    (FrontCategoryTreeVO<FrontCategoryEntity>)
                            redisTemplate.boundValueOps(CATEGORY_TREE_KEY).get();
            return treeVO;
        }
        // Redis中没有三级分类信息,表示本次为第一次访问,进行连接数据库查询数据并返回,再保存到Redis中
        List<CategoryStandardVO> categoryStandardVOS =
                dubboCategoryService.getCategoryList();
        // 将子分类对象保存到对应的父分类对象的children属性中
        FrontCategoryTreeVO<FrontCategoryEntity> treeVO =
                initTree(categoryStandardVOS);
        redisTemplate.boundValueOps(CATEGORY_TREE_KEY).set(treeVO, 1, TimeUnit.MINUTES);
        // 上面时间定义1分钟,用于学习测试,实际开发中一般保存较长时间
        return treeVO;
    }

    // 构建三级分类树
    private FrontCategoryTreeVO<FrontCategoryEntity> initTree(List<CategoryStandardVO> categoryStandardVOs) {

        // 一个父分类可能包含多个子分类
        Map<Long, List<FrontCategoryEntity>> map = new HashMap<>();
        log.info("当前分类对象总数量:{}", categoryStandardVOs.size());
        for (CategoryStandardVO categoryStandardVO : categoryStandardVOs) {
            FrontCategoryEntity frontCategoryEntity = new FrontCategoryEntity();
            BeanUtils.copyProperties(categoryStandardVO, frontCategoryEntity);
            Long parentId = categoryStandardVO.getParentId();
            if (map.containsKey(parentId)) {
                //如果当前ParentId存在,直接将子类添加入value中
                map.get(parentId).add(frontCategoryEntity);
            } else {
                // 如果没有key,就创建key-value
                List<FrontCategoryEntity> value = new ArrayList<>();
                value.add(frontCategoryEntity);
                map.put(parentId, value);
            }
        }
        // 将子分类对象关联到对应的父分类对象的children属性中
        // 先获得所有一级分类对象,即父分类id为0的对象
        List<FrontCategoryEntity> firstLevel = map.get(0L);
        if (firstLevel == null || firstLevel.isEmpty()) {
            throw new CoolSharkServiceException(
                    ResponseCode.INTERNAL_SERVER_ERROR,
                    "缺失一级分类"
            );
        }
        for (FrontCategoryEntity oneLevel : firstLevel) {
            Long secondLevelParentId = oneLevel.getId();
            List<FrontCategoryEntity> secondLevel = map.get(secondLevelParentId);
            if (secondLevel == null || secondLevel.isEmpty()) {
                log.debug("缺失二级分类");
                continue;
            }
            for (FrontCategoryEntity twoLevel : secondLevel) {
                Long thirdLevelParentId = twoLevel.getId();
                List<FrontCategoryEntity> thirdLevel = map.get(thirdLevelParentId);
                if (thirdLevel == null || thirdLevel.isEmpty()) {
                    log.debug("缺失三级分类");
                    continue;
                }
                twoLevel.setChildrens(thirdLevel);
            }
            oneLevel.setChildrens(secondLevel);
        }
        FrontCategoryTreeVO<FrontCategoryEntity> treeVO = new FrontCategoryTreeVO<>();
        treeVO.setCategories(firstLevel);
        return treeVO;

    }

}
