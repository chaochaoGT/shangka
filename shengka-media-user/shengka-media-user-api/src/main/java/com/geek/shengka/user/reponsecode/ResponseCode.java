package com.geek.shengka.user.reponsecode;

import com.geek.shengka.common.basemodel.ReturnBeanCode;

public enum ResponseCode implements ReturnBeanCode {

    /***********************************通用错误码*****************************************/
    OK(0, "请求成功"),
    SYSTEM_HEALTH(200, "success"),
    INVALID_FIELDS(1001, "请求参数非法"),
    SYSTEM_ERROR(2002, "系统错误或访问过于频繁"),
    USER_ERROR(3002, "用户信息不存在");

    private int code;
    private String message;


    private ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

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


    @Override
    public int getReturnCode() {
        return code;
    }

    @Override
    public String getReturnMessage() {
        return message;
    }

}
