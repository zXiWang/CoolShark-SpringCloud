package com.xiwang.csmall.product.pojo.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("pms_album")
public class AlbumAddNewDTO {
    private String name;
    private String description;
    private long sort;
}
