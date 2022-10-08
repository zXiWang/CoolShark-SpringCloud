package com.xiwang.csmall.product.web;

import com.xiwang.csmall.product.ex.ServiceException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<T> implements Serializable {

    /**
     * 状态
     */
    @ApiModelProperty("业务状态码")
    private Integer state;
    /**
     * 操作失败时的提示文本
     */
    @ApiModelProperty("操作失败时的提示文本")
    private String message;

    /**
     * 操作成功时的响应数据
     */
    @ApiModelProperty("操作成功时的响应数据")
    private T data;

    public static JsonResult ok() {
//        JsonResult jsonResult = new JsonResult();
//        jsonResult.state = ServiceCode.OK.getValue();
        return ok(null);
    }

    public static <T> JsonResult<T> ok(T data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.state = ServiceCode.OK.getValue();
        jsonResult.message = null;
        jsonResult.data = data;
        return jsonResult;
    }

    public static JsonResult fail(ServiceException e) {
        return fail(e.getServiceCode(), e.getMessage());
    }

    public static JsonResult fail(ServiceCode serviceCode, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.state = serviceCode.getValue();
        jsonResult.message = message;
        return jsonResult;
    }

}
