package com.wf.ew.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 测试
 * </p>
 *
 * @author emil
 * @since 2020-02-10
 */
@TableName("test_demo")
public class Demo implements Serializable {

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
     * 测试内容
     */
    @TableField("testContent")
    private String testContent;

    /**
     * 测试类型
     */
    @TableField("testType")
    private String testType;

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
    public String getTestContent() {
        return testContent;
    }

    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }
    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    @Override
    public String toString() {
        return "Demo{" +
        "rowGuid=" + rowGuid +
        ", operateUserName=" + operateUserName +
        ", operateDate=" + operateDate +
        ", testContent=" + testContent +
        ", testType=" + testType +
        "}";
    }
}
