package com.xiwang.csmall.product.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_brand_category")
public class BrandCategory {

    private long id;
    private long brandId;
    private long categoryId;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
