package com.geek.shengka.common.exception.auth;


import com.geek.shengka.common.constant.CommonConstants;
import com.geek.shengka.common.exception.BaseException;


public class ClientForbiddenException extends BaseException {
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }



}
