package com.geek.shengka.content.service;

import com.geek.shengka.content.request.PublishVoice;
import com.geek.shengka.content.request.VoiceAttend;
import com.geek.shengka.content.response.VoiceInfo;

import java.util.List;

import com.geek.shengka.content.request.ContentDeleteVoiceRequest;
import com.geek.shengka.content.request.ContentVoiceRequest;

/**
 * 语音接口
 */
public interface VoiceService {

	/** 根据视频获取语音列表 **/
	List<VoiceInfo> getRemoteVoices(ContentVoiceRequest param);

	/** 语音发布 **/
	String publishVoice(PublishVoice param);

	/** 语音删除 **/
	int delVoice(String voiceId);

	/** 语音@好友 **/
	int attentionFriends(VoiceAttend param);

}
