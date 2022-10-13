package com.xiwang.csmall.product.pojo.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_attribute")
public class AttributeAddNewDTO {

    private long templateId;
    private String name;
    private String description;
    private long type;
    private long inputType;
    private String unit;
    private long sort;
    private long isAllowCustomize;

}
