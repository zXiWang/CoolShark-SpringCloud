package com.xiwang.csmall.product.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("pms_spu_detail")
public class SpuDetail {

    private long id;
    private long spuId;
    private String detail;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
