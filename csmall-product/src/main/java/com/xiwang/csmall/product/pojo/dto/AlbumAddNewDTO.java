package com.xiwang.csmall.product.pojo.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 相册添加
 */
@Data
@TableName("pms_album")
public class AlbumAddNewDTO {

    @NotEmpty
    @NotNull(message = "添加相册失败，必须提交相册名称！")
    @ApiModelProperty(value = "相册名称", example = "xxx的相册名称", required = true)
    private String name;

    @ApiModelProperty(value = "相册简介", example = "xxx的相册简介")
    @NotNull(message = "添加相册失败，必须提交相册简介！")
    private String description;


    @ApiModelProperty(value = "排序序号", example = "xxx的相册排序序号")
    @Range(min = 0, max = 255, message = "添加相册失败,排序序号必须在[0,255]之间")
    private long sort;
}
