package com.wf.ew.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author emil
 * @since 2019-08-12
 */
@TableName("datasource")
public class Datasource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ds_id", type = IdType.AUTO)
    private Integer dsId;

    private String dsType;

    private String dsName;

    private String loginUser;

    private String loginPwd;

    private String serviceName;

    private String dbName;

    private String connectionString;

    private Date createDate;

    public Integer getDsId() {
        return dsId;
    }

    public void setDsId(Integer dsId) {
        this.dsId = dsId;
    }
    public String getDsType() {
        return dsType;
    }

    public void setDsType(String dsType) {
        this.dsType = dsType;
    }
    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }
    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }
    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Datasource{" +
        "dsId=" + dsId +
        ", dsType=" + dsType +
        ", dsName=" + dsName +
        ", loginUser=" + loginUser +
        ", loginPwd=" + loginPwd +
        ", serviceName=" + serviceName +
        ", dbName=" + dbName +
        ", connectionString=" + connectionString +
        ", createDate=" + createDate +
        "}";
    }
}
