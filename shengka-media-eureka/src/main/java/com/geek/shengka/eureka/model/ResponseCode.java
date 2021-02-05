package com.geek.shengka.eureka.model;

public enum ResponseCode implements ReturnCode {

    /***********************************通用错误码*****************************************/
    SYSTEM_HEALTH(0, "success");

    private int code;
    private String message;


    private ResponseCode(int code, String message) {
        this.code = code;
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
