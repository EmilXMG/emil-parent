package com.wf.ew.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 消息中心
 * </p>
 *
 * @author emil
 * @since 2020-02-09
 */
public class SysMessage implements Serializable {

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
     * 消息标题
     */
    @TableField("messageTitle")
    private String messageTitle;

    /**
     * 消息内容
     */
    @TableField("messageContent")
    private String messageContent;

    /**
     * 消息类型
     */
    @TableField("messageType")
    private String messageType;

    /**
     * 消息发送人ID
     */
    @TableField("fromUser")
    private String fromUser;

    /**
     * 消息发送人姓名
     */
    @TableField("fromUserName")
    private String fromUserName;

    /**
     * 接收人ID
     */
    @TableField("targetUser")
    private String targetUser;

    /**
     * 接收人姓名
     */
    @TableField("targetUserName")
    private String targetUserName;

    /**
     * 是否已读
     */
    @TableField("isRead")
    private String isRead;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发送日期
     */
    @TableField("sendDate")
    private Date sendDate;

    /**
     * 创建日期
     */
    @TableField("createDate")
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

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public String getTargetUserName() {
        return targetUserName;
    }

    public void setTargetUserName(String targetUserName) {
        this.targetUserName = targetUserName;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SysMessage{" +
                "rowGuid=" + rowGuid +
                ", operateUserName=" + operateUserName +
                ", operateDate=" + operateDate +
                ", messageTitle=" + messageTitle +
                ", messageContent=" + messageContent +
                ", messageType=" + messageType +
                ", fromUser=" + fromUser +
                ", fromUserName=" + fromUserName +
                ", targetUser=" + targetUser +
                ", targetUserName=" + targetUserName +
                ", isRead=" + isRead +
                ", remark=" + remark +
                ", sendDate=" + sendDate +
                ", createDate=" + createDate +
                "}";
    }
}
