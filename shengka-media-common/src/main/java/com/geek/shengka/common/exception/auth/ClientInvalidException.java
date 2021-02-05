package com.geek.shengka.common.exception.auth;

import com.geek.shengka.common.constant.CommonConstants;
import com.geek.shengka.common.exception.BaseException;

public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
