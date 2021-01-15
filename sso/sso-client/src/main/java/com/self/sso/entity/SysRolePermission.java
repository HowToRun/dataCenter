package com.self.sso.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2021-01-15
 */
@TableName("sys_role_permission")
public class SysRolePermission extends Model<SysRolePermission> {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 角色id
     */
    @TableField("sys_role_id")
    private Long sysRoleId;

    /**
     * 权限id
     */
    @TableField("sys_permission_id")
    private Long sysPermissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }
    public Long getSysPermissionId() {
        return sysPermissionId;
    }

    public void setSysPermissionId(Long sysPermissionId) {
        this.sysPermissionId = sysPermissionId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysRolePermission{" +
        "id=" + id +
        ", sysRoleId=" + sysRoleId +
        ", sysPermissionId=" + sysPermissionId +
        "}";
    }
}
