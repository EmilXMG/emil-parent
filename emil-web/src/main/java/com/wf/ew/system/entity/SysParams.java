package com.wf.ew.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 系统参数
 * </p>
 *
 * @author emil
 * @since 2020-02-08
 */
public class SysParams implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 全局唯一标识
     */
    @TableId(value = "rowGuid", type = IdType.UUID)
    private String rowGuid;

    /**
     * 操作用户
     */
    @TableField("operateUserName")
    private String operateUserName;

    /**
     * 操作日期
     */
    @TableField("operateDate")
    private Date operateDate;

    /**
     * 参数名
     */
    private String paramName;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 所属种类
     */
    private String categoryGuid;

    /**
     * 添加人
     */
    private Integer userId;

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryGuid() {
        return categoryGuid;
    }

    public void setCategoryGuid(String categoryGuid) {
        this.categoryGuid = categoryGuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SysParams{" +
                "rowGuid=" + rowGuid +
                ", operateUserName=" + operateUserName +
                ", operateDate=" + operateDate +
                ", paramName=" + paramName +
                ", paramValue=" + paramValue +
                ", description=" + description +
                ", categoryGuid=" + categoryGuid +
                ", userId=" + userId +
                "}";
    }
}
