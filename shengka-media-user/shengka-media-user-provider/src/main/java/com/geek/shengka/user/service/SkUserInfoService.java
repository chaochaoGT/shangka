package com.geek.shengka.user.service;

import com.geek.shengka.user.entity.vo.SkUserBaseInfoUpdateVO;
import com.geek.shengka.user.entity.vo.SkUserBaseInfoVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/7 11:25
 */
public interface SkUserInfoService {
    /**
     * 通过UserId来查询个人中心详情
     *
     * @param userId
     * @return com.geek.shengka.user.entity.vo.SkUserBaseInfoVO
     * @author qubianzhong
     * @Date 11:34 2019/8/7
     */
    SkUserBaseInfoVO center(Long userId);

    /**
     * 通过UserId来查询个人主页详情
     *
     * @param userId
     * @param attentionUserId
     * @return com.geek.shengka.user.entity.vo.SkUserBaseInfoVO
     * @author qubianzhong
     * @Date 13:49 2019/8/7
     */
    SkUserBaseInfoVO info(Long userId, Long attentionUserId);

    /**
     * @param extendVO
     * @param request
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 10:43 2019/8/8
     */
    Boolean update(SkUserBaseInfoUpdateVO extendVO, HttpServletRequest request);

    /**
     * 通过声咖号查询USerId
     *
     * @param skCode
     * @return java.lang.Long
     * @author qubianzhong
     * @Date 13:43 2019/8/8
     */
    Long selectUserIdBySkCode(String skCode);

    SkUserBaseInfoVO baseInfo(Long userId);

    /**
     * 黑名单用户IDS
     *
     * @param userId
     * @return java.util.List<java.lang.Long>
     * @author qubianzhong
     * @Date 17:12 2019/8/15
     */
    List<Long> blackListIds(Long userId);
}
