package com.xiwang.csmall.product.pojo.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("pms_sku")
public class SkuNormalVO {

    private long id;
    private long spuId;
    private String title;
    private String barCode;
    private long attributeTemplateId;
    private String specifications;
    private long albumId;
    private String pictures;
    private double price;
    private long stock;
    private long stockThreshold;
    private long sales;
    private long commentCount;
    private long positiveCommentCount;
    private long sort;


}
