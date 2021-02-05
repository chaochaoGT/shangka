package com.geek.shengka.gold.rabbitmq.entity;

import java.io.Serializable;

public class TaskEvent implements Serializable{

	private static final long serialVersionUID = 7381193930600141408L;

	private int type;	//	1-发布视频  2-语音评论  3-关注用户  4-登录签到
	
	private long userId;	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}


}
