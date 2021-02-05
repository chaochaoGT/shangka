package com.geek.shengka.common.mq;

public class DataGrandItemMsg {
	public static final String CMD_UPDATE="update";
	public static final String CMD_DELETE="delete";
	private String cmd; //update:修改，delete：删除
	private String itemid;	//视频id
	private String title; // 标题
	private String cateid; // 分类
	private String itemTags; // 标签
	private Long itemModifyTime; // 修改时间,
	private String publisher;// 发布者,上传者
	private String giveThumbsNums; // 点赞数
	private String commentNums;// 评论数
	private String hasBeenCollected;// 收藏数
	private String watchedTimes;// 观看次数
	private String reportTimes; // 举报次数
	private String duration; // 持续时间
	private String contentType;
	private String isOriginal;// 是否原创
	private String size; // 文件大小
	private String authorId;// 视频原创者id
	private String status;// 状态
	
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCateid() {
		return cateid;
	}
	public void setCateid(String cateid) {
		this.cateid = cateid;
	}
	public String getItemTags() {
		return itemTags;
	}
	public void setItemTags(String itemTags) {
		this.itemTags = itemTags;
	}
	public Long getItemModifyTime() {
		return itemModifyTime;
	}
	public void setItemModifyTime(Long itemModifyTime) {
		this.itemModifyTime = itemModifyTime;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getGiveThumbsNums() {
		return giveThumbsNums;
	}
	public void setGiveThumbsNums(String giveThumbsNums) {
		this.giveThumbsNums = giveThumbsNums;
	}
	public String getCommentNums() {
		return commentNums;
	}
	public void setCommentNums(String commentNums) {
		this.commentNums = commentNums;
	}
	public String getHasBeenCollected() {
		return hasBeenCollected;
	}
	public void setHasBeenCollected(String hasBeenCollected) {
		this.hasBeenCollected = hasBeenCollected;
	}
	public String getWatchedTimes() {
		return watchedTimes;
	}
	public void setWatchedTimes(String watchedTimes) {
		this.watchedTimes = watchedTimes;
	}
	public String getReportTimes() {
		return reportTimes;
	}
	public void setReportTimes(String reportTimes) {
		this.reportTimes = reportTimes;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getIsOriginal() {
		return isOriginal;
	}
	public void setIsOriginal(String isOriginal) {
		this.isOriginal = isOriginal;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
