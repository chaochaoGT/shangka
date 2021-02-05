package com.geek.shengka.gold.rabbitmq.entity;

import java.io.Serializable;

public class GetTaskCoinEvent implements Serializable{

	private static final long serialVersionUID = -7186681360549912574L;
    
	private long taskConfigId;
	
	private long userId;
	
	private long timeInMilliseconds ;

	public long getTaskConfigId() {
		return taskConfigId;
	}

	public void setTaskConfigId(long taskConfigId) {
		this.taskConfigId = taskConfigId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTimeInMilliseconds() {
		return timeInMilliseconds;
	}

	public void setTimeInMilliseconds(long timeInMilliseconds) {
		this.timeInMilliseconds = timeInMilliseconds;
	}

	
}
