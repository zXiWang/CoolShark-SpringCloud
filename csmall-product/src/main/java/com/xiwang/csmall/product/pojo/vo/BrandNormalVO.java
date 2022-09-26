package com.xiwang.csmall.product.pojo.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("pms_brand")
public class BrandNormalVO {

    private long id;
    private String name;
    private String pinyin;
    private String logo;
    private String description;
    private String keywords;
    private long sort;
    private long sales;
    private long productCount;
    private long commentCount;
    private long positiveCommentCount;
    private long enable;


}
