package cn.tedu.mall.pojo.product.dto;

import cn.tedu.mall.pojo.valid.product.CategoryRegExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class CategoryAddNewDTO implements CategoryRegExpression, Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "新增类别失败，";

    /**
     * 父级类别id，如果无父级，则为0
     */
    @ApiModelProperty(value = "父级类别id，如果无父级，则为0", required = true, dataType = "long", position = 1)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择父级类别！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的父级类别的数据格式错误！")
    private Long parentId;

    /**
     * 类别名称
     */
    @ApiModelProperty(value = "类别名称", required = true, position = 2)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写名称！")
    @Pattern(regexp = REGEXP_NAME, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_NAME)
    private String name;

    /**
     * 关键词列表，各关键词使用英文的逗号分隔
     */
    @ApiModelProperty(value = "关键词列表，各关键词使用英文的逗号分隔", required = true, position = 3)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写关键词列表！")
    @Pattern(regexp = REGEXP_KEYWORDS, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_KEYWORDS)
    private String keywords;

    /**
     * 图标图片的URL
     */
    @ApiModelProperty(value = "图标图片的URL", required = true, position = 4)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请输入图标图片的URL！")
    @Pattern(regexp = REGEXP_ICON, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_ICON)
    private String icon;

    /**
     * 是否启用，1=启用，0=未启用
     */
    @ApiModelProperty(value = "是否启用，1=启用，0=未启用", required = true, position = 5)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择是否启用！")
    @Range(max = 1, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_ENABLE)
    private Integer enable;

    /**
     * 是否显示在导航栏，1=启用，0=未启用
     */
    @ApiModelProperty(value = "是否显示在导航栏，1=启用，0=未启用", required = true, position = 6)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择是否显示在导航栏！")
    @Range(max = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的是否显示在导航栏的数据格式错误！")
    private Integer display;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", position = 7)
    @Range(max = 99, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_SORT)
    private Integer sort;

}
