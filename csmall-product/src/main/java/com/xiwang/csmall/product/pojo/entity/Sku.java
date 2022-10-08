package com.xiwang.csmall.product.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_sku")
public class Sku {

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
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
