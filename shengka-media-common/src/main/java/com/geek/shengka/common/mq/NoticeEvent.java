package com.geek.shengka.common.mq;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NoticeEvent implements Serializable{
	
	private static final long serialVersionUID = 1062573430605028409L;
	
	public static final Integer type_thumbsUp=1;//点赞
	public static final Integer type_attenttion=2;//关注

    public static final String variety_voice="voice";//语音
    public static final String variety_video="video";//视频
	
	private Integer type;
	private Long eventId;	//sk_fans.id或sk_thumbs_up.id
	private Long time;	//毫秒
    private String variety;//voice 语音，video 视频

	
	
}
