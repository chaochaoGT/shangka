package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkThumbsUp;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * SkThumbsUpDAO继承基类
 */
@Repository
public interface SkThumbsUpDAO extends MyBatisBaseDao<SkThumbsUp, Long> {
	/** 唯一性查询**/
	SkThumbsUp selectByUnique(SkThumbsUp param);
	
    /**
     * 根据用户uids获取当前用户的已关注的用户
     * @param userId
     * @param attentionUserIds
     * @return
     */
    List<String> selectByVoiceThumbsUps(@Param("userId")Long userId,@Param("voiceIds") List<String> voiceIds);
}