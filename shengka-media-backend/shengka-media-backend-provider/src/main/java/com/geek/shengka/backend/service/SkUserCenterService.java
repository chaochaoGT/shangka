package com.geek.shengka.backend.service;

import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.res.SkUserInfoResParam;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/7 10:02
 */
public interface SkUserCenterService {
    /**
     * 通过IDs查询用户列表
     *
     * @param userIds
     * @return java.util.List<com.geek.shengka.backend.params.res.SkUserInfoResParam>
     * @author qubianzhong
     * @Date 10:22 2019/8/7
     */
    List<SkUserInfoResParam> getInfosByIds(List<Long> userIds) throws BaseException;

    /**
     * 校验用户IDS
     *
     * @param ids
     * @return void
     * @author qubianzhong
     * @Date 20:42 2019/8/13
     */
    void checkUserIds(List<String> ids) throws BaseException;
}
