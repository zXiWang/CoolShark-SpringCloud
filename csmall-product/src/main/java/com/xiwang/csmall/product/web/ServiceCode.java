package com.xiwang.csmall.product.web;

/**
 * 业务状态码的枚举
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
public enum ServiceCode {

    OK(200),
    ERR_BAD_REQUEST(400),
    ERR_NOT_FOUND(404),
    ERR_CONFLICT(409),
    ERR_INSERT(500),
    ERR_DELETE(501),
    ERR_UPDATE(502),
    ERR_SELECT(503);

    private final Integer value;

    ServiceCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
