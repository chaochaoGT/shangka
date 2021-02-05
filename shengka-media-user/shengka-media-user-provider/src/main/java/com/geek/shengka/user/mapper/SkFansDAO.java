package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkFans;
import com.geek.shengka.user.entity.vo.SkFansVO;
import com.geek.shengka.user.request.FansRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * SkFansDAO继承基类
 */
@Repository
public interface SkFansDAO extends MyBatisBaseDao<SkFans, Long> {


    /**
     * 验证粉丝状态
     * @param userId
     * @param attentionUserId
     * @return
     */
    SkFans checkFansState(@Param("userId") Long userId,@Param("attentionUserId") Long attentionUserId);

    /**
     * 取关
     * @param userId
     * @param attentionUserId
     * @return
     */
    int deleteAttentionUser(Long userId, Long attentionUserId);

    /**
     * 根据用户id获取自己的关注列表或粉丝列表
     * @param params
     * @return
     */
    List<SkFansVO> attentionFansList(FansRequest params);

    /**
     * 关注用户搜索
     * @param params
     * @return
     */
    List<SkFansVO> findFansList(FansRequest params);

    /**
     * 根据用户uids获取当前用户的已关注的用户
     * @param userId
     * @param attentionUserIds
     * @return
     */
    List<String> selectMyFans(@Param("userId")Long userId,@Param("attentionUserIds") Set<String> attentionUserIds);
}