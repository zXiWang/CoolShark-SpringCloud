package com.xiwang.csmall.product.pojo.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("pms_spu_detail")
public class SpuDetailNormalVO {

    private long id;
    private long spuId;
    private String detail;


}
