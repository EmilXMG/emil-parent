package com.wf.ew.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("code_main")
public class CodeMain {
    @TableId(value = "code_id", type = IdType.AUTO)
    private int codeId;
    private String codeName;
    private String subsystemNum;
    private String description;
    private Date createDate;

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getSubsystemNum() {
        return subsystemNum;
    }

    public void setSubsystemNum(String subsystemNum) {
        this.subsystemNum = subsystemNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
