package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkFans;
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
     * 获取粉丝
     **/
    List<SkFans> selectByFans(SkFans skFans);

    /**
     * 通过当前用户ID，和需要查询的用户IDS，来搜索当前用户ID关注的用户IDS
     *
     * @param searchUserIds
     * @param userId
     * @return
     */
    List<String> getStateByUserIds(@Param("ids") List<String> searchUserIds,
                                          @Param("userId") Long userId);


    /**
     * 根据用户uids获取当前用户的已关注的用户
     * @param userId
     * @param attentionUserIds
     * @return
     */
    List<String> selectMyFans(@Param("userId")Long userId,@Param("attentionUserIds") Set<String> attentionUserIds);

    
    /**
     * 根据用户uids获取当前用户的已关注的用户
     * @param userId
     * @param attentionUserIds
     * @return
     */
    List<String> selectByVoiceFans(@Param("userId")Long userId,@Param("attentionUserIds") List<String> attentionUserIds);
}