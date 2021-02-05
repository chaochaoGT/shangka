package com.geek.shengka.common.exception.auth;

import com.geek.shengka.common.constant.CommonConstants;
import com.geek.shengka.common.exception.BaseException;


public class ImageCodeInvalidException extends BaseException {
    public ImageCodeInvalidException(String message) {
        super(message, CommonConstants.EX_IMAGECODE_CODE);
    }
}
