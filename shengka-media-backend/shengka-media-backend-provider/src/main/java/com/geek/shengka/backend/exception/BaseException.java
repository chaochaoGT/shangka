package com.geek.shengka.backend.exception;

import com.geek.shengka.backend.constant.CommonConstant;

/**
 * @author qubianzhong
 * @date 2019/7/19 16:40
 */
public class BaseException extends Exception {
    private int code;

    public BaseException() {
        super();
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
        this.code = CommonConstant.SYS_ERROR;
    }

    public int getCode() {
        return code;
    }
}
