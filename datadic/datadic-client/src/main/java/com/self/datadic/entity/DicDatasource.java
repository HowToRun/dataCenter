package com.self.datadic.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yihan.hu
 * @since 2020-12-12
 */
@TableName("dic_datasource")
public class DicDatasource extends Model<DicDatasource> {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 链接名称
     */
    private String name;
    /**
     * 数据库名
     */
    private String dataBase;

    /**
     * 数据库类型
     */
    private Integer type;

    /**
     * IP地址
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 数据库用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 链接参数
     */
    private String param;

    /**
     * 创建人
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField("update_user")
    private Long updateUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    public String getDataBase() {
        return dataBase;
    }

    public DicDatasource setDataBase(String dataBase) {
        this.dataBase = dataBase;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getParam() {
        return param;
    }

    public Integer getType() {
        return type;
    }

    public DicDatasource setType(Integer type) {
        this.type = type;
        return this;
    }

    public void setParam(String param) {
        this.param = param;
    }
    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DicDatasource{" +
        "id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", host=" + host +
        ", port=" + port +
        ", userName=" + userName +
        ", password=" + password +
        ", param=" + param +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        "}";
    }
}
