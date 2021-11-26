package com.wangfugui.apprentice.common.exception;

/**
 * 基础异常
 * @Author: MaSiyi
 * @Date: 2021/11/26
 */
public class BaseException extends RuntimeException {
    protected String code;
    protected String msg;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
        this.msg = message;
    }

    public BaseException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}