package com.xiwang.csmall.product.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_category")
public class Category {

  private long id;
  private String name;
  private long parentId;
  private long depth;
  private String keywords;
  private long sort;
  private String icon;
  private long enable;
  private long isParent;
  private long isDisplay;
  private LocalDateTime gmtCreate;
  private LocalDateTime gmtModified;

}
