package com.xiwang.csmall.product.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("pms_attribute_template")
public class AttributeTemplate {

    private long id;
    private String name;
    private String pinyin;
    private String keywords;
    private long sort;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
