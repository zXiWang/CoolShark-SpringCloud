package com.xiwang.csmall.passport.web;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult implements Serializable {

    /**
     * 状态
     */
    private Integer state;
    /**
     * 操作失败时的提示文本
     */
    private String message;

    public static JsonResult ok() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.state = ServiceCode.OK.getValue();
        return jsonResult;
    }

    public static JsonResult fail(ServiceCode serviceCode, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.state = serviceCode.getValue();
        jsonResult.message = message;
        return jsonResult;
    }

}
