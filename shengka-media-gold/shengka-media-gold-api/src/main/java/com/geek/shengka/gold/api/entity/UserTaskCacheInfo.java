package com.geek.shengka.gold.api.entity;

public class UserTaskCacheInfo {
	
	public static final Integer status_take =1 ;	//领取
	
	public static final Integer status_goComment =2;	//去评论	
	
	public static final Integer status_goUpload =3;		//去上传
	
	public static final Integer status_goAttention =4;	//去关注

	public static final Integer status_hadTook =5;		//已领取
	
	private Long userId;
	private Long taskId;
	private Integer completeCount;
	private Integer status;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Integer getCompleteCount() {
		return completeCount;
	}
	public void setCompleteCount(Integer completeCount) {
		this.completeCount = completeCount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	
}
