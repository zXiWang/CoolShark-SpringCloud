package com.xiwang.csmall.product.pojo.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("pms_attribute_template")
public class AttributeTemplateAddNewDTO {

    private String name;
    private String pinyin;
    private String keywords;
    private long sort;


}
