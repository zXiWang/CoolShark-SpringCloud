package com.xiwang.csmall.product.pojo.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("pms_brand")
public class BrandAddNewDTO {

    private String name;
    private String pinyin;
    private String logo;
    private String description;
    private String keywords;
    private long sort;
    private long enable;


}
