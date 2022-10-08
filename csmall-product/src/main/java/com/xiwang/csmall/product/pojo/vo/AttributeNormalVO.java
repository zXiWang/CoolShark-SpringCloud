package com.xiwang.csmall.product.pojo.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("pms_attribute")
public class AttributeNormalVO {

    private long id;
    private long templateId;
    private String name;
    private String description;
    private long type;
    private long inputType;
    private String valueList;
    private String unit;
    private long sort;
    private long isAllowCustomize;


}
