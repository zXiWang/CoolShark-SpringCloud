package com.xiwang.csmall.product.web;

/**
 * 业务状态码的枚举
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
public enum ServiceCode {

    /**
     * 成功
     */
    OK(200),
    /**
     * 错误：请求参数有误
     */
    ERR_BAD_REQUEST(400),
    /**
     * 错误：未认证
     */
    ERR_UNAUTHORIZED(401),
    /**
     * 错误：未认证，账号已被禁用
     */
    ERR_UNAUTHORIZED_DISABLED(40101),
    /**
     * 错误：无操作权限，禁止访问
     */
    ERR_FORBIDDEN(403),
    /**
     * 错误：尝试访问的数据不存在
     */
    ERR_NOT_FOUND(404),
    /**
     * 错误：请求方式错误
     */
    ERR_NOT_ALLOWED(405),
    /**
     * 错误：数据冲突
     */
    ERR_CONFLICT(409),
    /**
     * 错误：插入数据错误
     */
    ERR_INSERT(500),
    /**
     * 错误：删除数据错误
     */
    ERR_DELETE(501),
    /**
     * 错误：更新数据错误
     */
    ERR_UPDATE(502),
    /**
     * 错误：查询数据错误
     */
    ERR_SELECT(503),
    /**
     * 错误：JWT签名错误
     */
    ERR_JWT_SIGNATURE(600),
    /**
     * 错误：JWT数据格式错误
     */
    ERR_JWT_MALFORMED(601),
    /**
     * 错误：JWT已过期
     */
    ERR_JWT_EXPIRED(602),
    /**
     * 错误：未知错误
     */
    ERR_UNKNOWN(999);

    private Integer value;

    ServiceCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }


}
