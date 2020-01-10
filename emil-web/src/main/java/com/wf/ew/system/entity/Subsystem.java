package com.wf.ew.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;


@TableName("sys_subsystem")
public class Subsystem implements Serializable {
    @TableId(value = "subsystem_id", type = IdType.AUTO)
    private int subsystemId;

    private String subsystemName;

    private String subsystemNum;

    private String orderNum;

    private Date createDate;

    public int getSubsystemId() {
        return subsystemId;
    }

    public void setSubsystemId(int subsystemId) {
        this.subsystemId = subsystemId;
    }

    public String getSubsystemName() {
        return subsystemName;
    }

    public void setSubsystemName(String subsystemName) {
        this.subsystemName = subsystemName;
    }

    public String getSubsystemNum() {
        return subsystemNum;
    }

    public void setSubsystemNum(String subsystemNum) {
        this.subsystemNum = subsystemNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
