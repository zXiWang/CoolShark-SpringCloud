package com.xiwang.csmall.passport.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("ams_admin")
public class AdminLoginInfoVO implements Serializable {
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
     * 手机号码
     */
    private String phone;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;
    /**
     * 权限
     */
    private List<String> permissions;
}
