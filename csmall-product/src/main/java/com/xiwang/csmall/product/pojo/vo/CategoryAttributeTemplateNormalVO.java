package com.xiwang.csmall.product.pojo.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_category_attribute_template")
public class CategoryAttributeTemplateNormalVO {

  private long id;
  private long categoryId;
  private long attributeTemplateId;


}
