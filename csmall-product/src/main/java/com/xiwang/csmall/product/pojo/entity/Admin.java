package com.xiwang.csmall.product.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 *
 */
@Data
@TableName("ams_admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String phone;
    private String email;
    private String description;
    private long enable;
    private String lastLoginIp;
    private long loginCount;

    private LocalDateTime gmtLastLogin;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

}
