package com.xiwang.csmall.passport.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员角色关联(AdminRole)实体类
 *
 * @author 夕妄
 * @since 2022-10-09 17:02:51
 */
@Data
@TableName("ams_admin_role")
public class AdminRole implements Serializable {
    private static final long serialVersionUID = -72281704903116068L;
    
    private Long id;
    /**
     * 管理员id
     */
    private Long adminId;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 数据创建时间
     */
    private Date gmtCreate;
    /**
     * 数据最后修改时间
     */
    private Date gmtModified;


}

