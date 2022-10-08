package com.xiwang.csmall.product.pojo.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("pms_sku_specification")
public class SkuSpecificationListItemVO {

    private long id;
    private long skuId;
    private long attributeId;
    private String attributeName;
    private String attributeValue;
    private String unit;
    private long sort;


}
