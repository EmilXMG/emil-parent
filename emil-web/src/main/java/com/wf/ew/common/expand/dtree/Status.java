package com.wf.ew.common.expand.dtree;

/**
 * 信息状态类
 *
 * @author emil
 */
public class Status {
    /**
     * 状态码
     */
    private int code = 200;
    /**
     * 信息标识
     */
    private String message = "success";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

