package com.xiwang.csmall.passport.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 管理员(Admin)实体类
 *
 * @author 夕妄
 * @since 2022-09-29 10:33:55
 */
@Data
public class AdminAddNewDTO implements Serializable {
    private static final long serialVersionUID = -50152907884194151L;
    
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码（密文）
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像URL
     */
    private String avatar;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 电子邮箱
     */
    private String email;


}

