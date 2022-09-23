package com.xiwang.csmall.product.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_attribute")
public class Attribute {

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
  private LocalDateTime gmtCreate;
  private LocalDateTime gmtModified;

}
