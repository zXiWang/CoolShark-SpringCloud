package com.xiwang.csmall.product.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_picture")
public class Picture {

  private long id;
  private long albumId;
  private String url;
  private String description;
  private long width;
  private long height;
  private long isCover;
  private long sort;
  private LocalDateTime gmtCreate;
  private LocalDateTime gmtModified;

}
