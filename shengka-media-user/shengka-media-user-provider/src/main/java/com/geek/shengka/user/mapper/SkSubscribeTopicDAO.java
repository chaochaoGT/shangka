package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkSubscribeTopic;
import com.geek.shengka.user.entity.vo.SkTopicConfigVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkSubscribeTopicDAO继承基类
 */
@Repository
public interface SkSubscribeTopicDAO extends MyBatisBaseDao<SkSubscribeTopic, Long> {
    SkSubscribeTopic selectByTopicIdUid(@Param("topicId") Long topicId, @Param("userId") Long userId);

    void deleteByTopicUid(@Param("topicId") Long topicId, @Param("userId") Long userId);

    /**
     * 当前用户的话题
     * @param userId
     * @param offset
     * @param pageSize
     * @return
     */
    List<SkTopicConfigVO> attentionTopicList(@Param("userId")Long userId,
                                             @Param("offset") int offset,
                                             @Param("pageSize") int pageSize);

}