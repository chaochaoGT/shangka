package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkUserBaseInfo;
import com.geek.shengka.user.entity.vo.SkUserBaseInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * SkUserBaseInfoDAO继承基类
 */
@Repository
public interface SkUserBaseInfoDAO extends MyBatisBaseDao<SkUserBaseInfo, Long> {

    /**
     * 更新用户基础数据
     */
    int updateUserBaseNum(Map<String, Object> map);

    /**
     * 通过UserId来查询个人中心详情
     *
     * @param userId
     * @return com.geek.shengka.user.entity.vo.SkUserBaseInfoVO
     * @author qubianzhong
     * @Date 11:35 2019/8/7
     */
    SkUserBaseInfoVO center(@Param(value = "userId") Long userId);

    /**
     * 通过UserId来查询个人主页详情
     *
     * @param userId
     * @return com.geek.shengka.user.entity.vo.SkUserBaseInfoVO
     * @author qubianzhong
     * @Date 13:51 2019/8/7
     */
    SkUserBaseInfoVO info(@Param(value = "userId") Long userId,
                          @Param(value = "attentionUserId") Long attentionUserId);

    /**
     * 通过UserId进行更新
     *
     * @param userBaseInfo
     * @return int
     * @author qubianzhong
     * @Date 13:45 2019/8/8
     */
    int updateByUserIdSelective(SkUserBaseInfo userBaseInfo);

    /**
     * 通过声咖号查找UserId
     *
     * @param skCode
     * @return java.lang.Long
     * @author qubianzhong
     * @Date 13:44 2019/8/8
     */
    Long selectUserIdBySkCode(String skCode);

    /**
     * 批量查询用户信息
     *
     * @param noticeUserIds
     * @return
     */
    List<SkUserBaseInfo> selectUserInfoByIds(@Param("noticeUserIds") List<String> noticeUserIds);

    /**
     * 黑名单用户IDS
     *
     * @param userId
     * @return java.util.List<java.lang.Long>
     * @author qubianzhong
     * @Date 17:15 2019/8/15
     */
    List<Long> blackListIds(Long userId);

    int count(@Param("userId") Long attentionUserId);
}