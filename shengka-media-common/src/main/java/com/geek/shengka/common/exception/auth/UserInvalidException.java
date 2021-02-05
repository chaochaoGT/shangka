package com.geek.shengka.common.exception.auth;


import com.geek.shengka.common.constant.CommonConstants;
import com.geek.shengka.common.exception.BaseException;

/**
* @author: bolei
* @date：2018年5月7日 上午8:28:11 
* @description：用户账号异常
*/

public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_PASS_INVALID_CODE);
    }
}
