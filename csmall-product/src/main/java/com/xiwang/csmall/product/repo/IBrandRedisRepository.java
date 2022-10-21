package com.xiwang.csmall.product.repo;

import com.xiwang.csmall.product.pojo.vo.BrandListItemVO;
import com.xiwang.csmall.product.pojo.vo.BrandNormalVO;

import java.util.List;

/**
 * 处理品牌缓存的数据访问接口
 *
 * @author 夕妄
 * @version 0.0.1
 */
public interface IBrandRedisRepository {

    /**
     * 品牌相关数据在Redis中的前缀
     */
    String BRAND_KEY_PREFIX = "brand:";
    /**
     * 品牌数据项在Redis中的Key前缀
     */
    String BRAND_ITEM_KEY_PREFIX = BRAND_KEY_PREFIX + "item:";
    /**
     * 品牌列表在Redis中的Key
     */
    String BRAND_LIST_KEY = BRAND_KEY_PREFIX + "list";
    /**
     * 所有品牌数据的Key
     */
    String BRAND_ALL_KEYS_KEY = BRAND_KEY_PREFIX + "all-keys";

    /**
     * 向Redis中写入品牌数据
     *
     * @param brandNormalVO 品牌数据
     */
    void save(BrandNormalVO brandNormalVO);

    /**
     * 向Redis中写入品牌列表
     *
     * @param brands 品牌列表
     */
    void save(List<BrandListItemVO> brands);

    /**
     * 从Redis中读取品牌数据
     *
     * @param id 品牌id
     * @return 匹配的品牌数据，如果没有匹配的数据，则返回null
     */
    BrandNormalVO get(Long id);

    /**
     * 从Redis中读取品牌列表
     *
     * @return 品牌列表
     */
    List<BrandListItemVO> list();

    /**
     * 从Redis中读取品牌列表
     *
     * @param start 读取数据的起始下标
     * @param end   读取数据的截止下标
     * @return 品牌列表
     */
    List<BrandListItemVO> list(long start, long end);

    /**
     * 删除Redis中全部品牌数据，包括各品牌详情数据和品牌列表等
     *
     * @return 成功删除的数据的数量
     */
    Long deleteAll();

}
