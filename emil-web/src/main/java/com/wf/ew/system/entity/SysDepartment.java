package com.wf.ew.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author emil
 * @since 2020-02-15
 */
public class SysDepartment implements Serializable {

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
     * 部门名称
     */
    private String departmentName;

    /**
     * 父id,-1表示无父级
     */
    private String parentId;

    /**
     * 部门CODE
     */
    private String departmentCode;

    /**
     * 排序
     */
    private byte[] orderNumber;

    /**
     * 创建日期
     */
    private Date createDate;

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public byte[] getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(byte[] orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SysDepartment{" +
                "rowGuid=" + rowGuid +
                ", operateUserName=" + operateUserName +
                ", operateDate=" + operateDate +
                ", departmentName=" + departmentName +
                ", parentId=" + parentId +
                ", departmentCode=" + departmentCode +
                ", orderNumber=" + orderNumber +
                ", createDate=" + createDate +
                "}";
    }
}
