package com.geek.shengka.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qubianzhong
 * @date 2019/8/2 13:53
 */
@Component
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 通过token获取用户的ID
     *
     * @param
     * @return java.lang.String
     * @author qubianzhong
     * @Date 13:58 2019/8/2
     */
    protected String getUserId() {
        Object userId = request.getAttribute("loginName");
        return userId != null ? userId.toString() : "";
    }
}
