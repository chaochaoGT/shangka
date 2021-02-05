package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkVoice;
import com.geek.shengka.content.response.VoiceInfo;
import com.geek.shengka.content.response.VoiceParam;
import com.geek.shengka.content.response.VoiceUserParam;

import feign.Param;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * SkVoiceDAO继承基类
 */
@Repository
public interface SkVoiceDAO extends MyBatisBaseDao<SkVoice, String> {
	
	/** 根据视频id获取语音**/
	List<SkVoice> selectByVideoId(String videoId);
	
	
	/** 根据视频id获取语音**/
	List<VoiceInfo> selectByVoices(@Param("videoId") String videoId, @Param("pageIndex") Integer pageIndex);
	
	/** 根据视频id获取语音**/
	VoiceParam selectByVoiceIds(@Param("videoId") String videoId, @Param("pageIndex") Integer pageIndex);
	
    
    /**
     * 根据视频ids查询详情
     * @param vids
     * @return
     */
    List<VoiceUserParam> selectByUserCount(@Param("list") List<String> list);
    
    
}