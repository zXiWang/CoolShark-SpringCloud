package com.xiwang.csmall.product.pojo.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_spu")
public class SpuNormalVO {

    private long id;
    private String name;
    private String typeNumber;
    private String title;
    private String description;
    private double listPrice;
    private long stock;
    private long stockThreshold;
    private String unit;
    private long brandId;
    private String brandName;
    private long categoryId;
    private String categoryName;
    private long attributeTemplateId;
    private long albumId;
    private String pictures;
    private String keywords;
    private String tags;
    private long sales;
    private long commentCount;
    private long positiveCommentCount;
    private long sort;
    private long isDeleted;
    private long isPublished;
    private long isNewArrival;
    private long isRecommend;
    private long isChecked;
    private String checkUser;
    private LocalDateTime gmtCheck;


}
