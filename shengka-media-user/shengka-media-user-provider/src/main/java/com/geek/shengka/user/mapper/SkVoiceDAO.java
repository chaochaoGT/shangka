package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkVoice;
import com.geek.shengka.user.entity.vo.SkVoiceVideoInfo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkVoiceDAO继承基类
 */
@Repository
public interface SkVoiceDAO extends MyBatisBaseDao<SkVoice, String> {

    /***
     * 查询我的发声列表
     * @param userId
     * @param start
     * @param size
     * @return
     */
    List<SkVoice> myVoiceList(@Param("userId") Long userId, @Param("start") Integer start, @Param("size") Integer size);
    
    SkVoiceVideoInfo selectVoiceVideoByVoiceId(@Param("voiceId")String voiceId);
}