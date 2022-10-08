package com.xiwang.csmall.product.pojo.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 添加属性模板
 */
@Data
@TableName("pms_attribute_template")
public class AttributeTemplateAddNewDTO {

    @ApiModelProperty(value = "属性模板名称", example = "xxx的属性模板名称",required = true)
    private String name;

    @ApiModelProperty(value = "拼音", example = "xxx的属性模板拼音")
    private String pinyin;

    @ApiModelProperty(value = "关键字", example = "xxx的属性模板关键字")
    private String keywords;

    @ApiModelProperty(value = "排序序号", example = "xxx的属性模板排序序号")
    private long sort;


}
