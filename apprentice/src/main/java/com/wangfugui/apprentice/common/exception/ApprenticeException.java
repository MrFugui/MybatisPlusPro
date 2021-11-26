package com.wangfugui.apprentice.common.exception;

/**
 * 普通异常类
 * @Author: MaSiyi
 * @Date: 2021/11/26
 */
public class ApprenticeException extends BaseException{

    public ApprenticeException() {
        super();
    }

    public ApprenticeException(String message) {
        super(message);
        this.msg=message;
    }

    public ApprenticeException(String code, String msg) {
        super(msg);
        this.msg=msg;
        this.code=code;
    }


}
