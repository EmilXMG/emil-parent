package com.wf.ew.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 便签信息
 * </p>
 *
 * @author emil
 * @since 2019-09-14
 */
public class SysMemo implements Serializable {

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
     * 创建人
     */
    private Integer userId;

    /**
     * 便签内容
     */
    private String memoContent;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SysMemo{" +
                "rowGuid=" + rowGuid +
                ", operateUserName=" + operateUserName +
                ", operateDate=" + operateDate +
                ", userId=" + userId +
                ", memoContent=" + memoContent +
                ", createDate=" + createDate +
                "}";
    }
}
