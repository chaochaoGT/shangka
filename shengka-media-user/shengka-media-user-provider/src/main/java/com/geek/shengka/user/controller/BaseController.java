package com.geek.shengka.user.controller;

import com.geek.shengka.common.constant.CommonConstants;
import com.geek.shengka.common.enums.AuthServiceStatusEnum;
import com.geek.shengka.common.exception.BaseException;
import com.geek.shengka.common.util.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qubianzhong
 * @date 2019/8/15 15:12
 */

@RestController
public class BaseController {

    @Autowired
    private HttpServletRequest request;


    protected Long getUserId() {
        Object uidObj = request.getAttribute(CommonConstants.CONTEXT_KEY_UID);
        Long userId;
        if (uidObj != null) {
            return Long.valueOf(uidObj.toString().trim());
        } else {
            userId = UserContextHolder.getUserId();
        }
        if (userId != null && userId < 1) {
            userId = null;
        }
        return userId;
    }

    protected boolean checkUserId(Long userId) {
        if (userId == null) {
            throw new BaseException(AuthServiceStatusEnum.AUTH_FAIL.getMessage(), AuthServiceStatusEnum.AUTH_FAIL.getCode());
        }
        return true;
    }
}
