package com.geek.shengka.content.service;

import java.util.List;

import com.geek.shengka.content.entity.SkVoice;
import com.geek.shengka.content.request.PublishVoice;
import com.geek.shengka.content.request.ThumbsUpRequest;
import com.geek.shengka.content.request.VoiceAttend;
import com.geek.shengka.content.request.ContentVoiceRequest;

/**
 *   点赞接口
 */
public interface ThumbsUpService {
	 
	/** 点赞语音**/
     public int thumbsUpVoice(ThumbsUpRequest param);
     
 
     
}
