package com.xiwang.csmall.product.pojo.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

import java.io.Serializable;

@Data
@TableName("pms_album")
public class AlbumNormalVO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private long sort;

}
