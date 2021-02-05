package com.geek.shengka.common.exception.auth;


import com.geek.shengka.common.constant.CommonConstants;
import com.geek.shengka.common.exception.BaseException;

/**
 * Created by ace on 2017/9/8.
 */
public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
