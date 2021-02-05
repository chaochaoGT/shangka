package com.geek.shengka.content.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class ContentFriendMqRequest implements Serializable{
	
	private static final long serialVersionUID = 6993043418619501970L;
	// 1-用户发语音评论视频  2-用户发视频@被通知人  3-用户语音@被通知人
	public static final  Integer type_publishVoiceComment=1;
	
	public static final  Integer type_publishVideoAt=2;
	
	public static final  Integer type_publishVoiceAt=3;
	
	private String mediaId;	//语音id或视频id
	private Long userId;	//发布语音评论的用户id或被@的用户id
	private Integer type;
	private Long time;	//毫秒
	 
}
