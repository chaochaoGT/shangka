package com.geek.shengka.content.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ThumbsUpMqRequest {
	
	public static final Integer type_thumbsUp=1;//点赞
	public static final Integer type_attenttion=2;//关注
	
	private Integer type;
	private Long eventId;
	private Long time;	//毫秒
 
}
