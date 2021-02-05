package com.geek.shengka.content.request;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VideoMqRequest implements Serializable{

	private static final long serialVersionUID = 7364695037558949853L;

	private int type;	//	1-发布视频  2-语音评论  3-关注用户  4-登录签到
	
	private long userId;	
}
