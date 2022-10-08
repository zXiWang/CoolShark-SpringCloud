package com.xiwang.csmall.product.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_sku_specification")
public class SkuSpecification {

    private long id;
    private long skuId;
    private long attributeId;
    private String attributeName;
    private String attributeValue;
    private String unit;
    private long sort;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
