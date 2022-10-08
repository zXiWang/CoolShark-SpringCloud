package com.xiwang.csmall.product.pojo.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("pms_attribute_template")
public class AttributeTemplateListItemVO {

    private long id;
    private String name;
    private String pinyin;
    private long sort;


}
