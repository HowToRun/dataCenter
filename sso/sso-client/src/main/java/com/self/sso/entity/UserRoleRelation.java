package com.self.sso.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2020-12-10
 */
@TableName("user_role_relation")
public class UserRoleRelation extends Model<UserRoleRelation> {

    private static final long serialVersionUID = 1L;

    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField("user_id")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "UserRoleRelation{" +
        "id=" + id +
        ", roleId=" + roleId +
        ", userId=" + userId +
        "}";
    }
}
