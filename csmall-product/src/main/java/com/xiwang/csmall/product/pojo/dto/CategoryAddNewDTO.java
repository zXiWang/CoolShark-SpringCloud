package com.xiwang.csmall.product.pojo.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_category")
public class CategoryAddNewDTO {

  private String name;
  private long parentId;
  private String keywords;
  private long sort;
  private String icon;
  private long enable;
  private long isDisplay;


}
