package com.xiwang.csmall.product.pojo.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_picture")
public class PictureNormalVO {

  private long id;
  private long albumId;
  private String url;
  private String description;
  private long width;
  private long height;
  private long isCover;
  private long sort;


}
