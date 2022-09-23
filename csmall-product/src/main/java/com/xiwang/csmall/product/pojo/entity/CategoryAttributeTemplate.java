package com.xiwang.csmall.product.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_category_attribute_template")
public class CategoryAttributeTemplate {

  private long id;
  private long categoryId;
  private long attributeTemplateId;
  private LocalDateTime gmtCreate;
  private LocalDateTime gmtModified;

}
