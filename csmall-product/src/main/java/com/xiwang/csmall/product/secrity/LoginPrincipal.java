package com.xiwang.csmall.product.secrity;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录用户
 *
 * @author 夕妄
 * @since 2022-09-29 10:33:55
 */
@Data
public class LoginPrincipal implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
}

