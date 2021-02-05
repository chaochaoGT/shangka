package com.geek.shengka.user.rabbitmq.entity;

import java.io.Serializable;

public class CommentAndAtNoticeEvent implements Serializable{
	
	private static final long serialVersionUID = 6993043418619501970L;
	// 1-用户发语音评论视频  2-用户发视频@被通知人  3-用户语音@被通知人
	public static final  Integer TYPE_PUBLISHVOICECOMMENT=1;
	
	public static final  Integer TYPE_PUBLISHVIDEOAT=2;
	
	public static final  Integer TYPE_PUBLISHVOICEAT=3;
	
	private String mediaId;	//语音id或视频id
	private Long userId;	//发布语音评论的用户id或被@的用户id
	private Integer type;
	private Long time;	//毫秒
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
	
}
