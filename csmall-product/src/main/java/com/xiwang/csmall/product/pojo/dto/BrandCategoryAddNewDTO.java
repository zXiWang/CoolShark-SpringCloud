package com.xiwang.csmall.product.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 品牌与类别关联(PmsBrandCategory)实体类
 *
 * @author 夕妄
 * @since 2022-09-26 09:11:07
 */
@Data
@TableName("pms_brand_category")
public class BrandCategoryAddNewDTO implements Serializable {
    private static final long serialVersionUID = -52572693846841583L;
    /**
     * 记录id
     */
    private Long id;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 类别id
     */
    private Long categoryId;

}

