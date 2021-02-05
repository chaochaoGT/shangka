package com.geek.shengka.common.request;

public class ContentActionData {
	private String contentsId;
	private String contentsType;
	private Long userId;
	private Event event;


    public String getContentsId() {
        return contentsId;
    }

    public void setContentsId(String contentsId) {
        this.contentsId = contentsId;
    }

    public String getContentsType() {
        return contentsType;
    }

    public void setContentsType(String contentsType) {
        this.contentsType = contentsType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(String eventId,String type,String name,String remark) {
        this.event = Event.builder().eventId(eventId).type(type).name(name).remark(remark);
    }
    public void setEvent(String eventId,String type,String name,String remark,String sceneType) {
        this.event = Event.builder().eventId(eventId).type(type).name(name).remark(remark).sceneType(sceneType);
    }
    public void setEventPlay(String sceneType,String recRequestId,String playTime) {
        this.event = event.sceneType(sceneType).recRequestId(recRequestId).playTime(playTime);
    }
}


class Event{
    private String eventId;
    private String type;
    private String name;
    private String remark;
    /** 页面来源：home：首页，mini_video：小视频页，category_video：分类页*/
    private String sceneType;
    /** 达观推荐请求id*/
    private String recRequestId;
    /**播放时长*/
    private String playTime;
    public static Event builder() {
        return new Event();
    }

    public String getEventId() {
        return eventId;
    }

    public Event eventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getType() {
        return type;
    }

    public Event type(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public Event name(String name) {
        this.name = name;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Event remark(String remark) {
        this.remark = remark;
        return this;
    }


    public Event sceneType(String sceneType) {
        this.sceneType=sceneType;
        return this;
    }
public Event recRequestId(String recRequestId) {
        this.recRequestId=recRequestId;
        return this;
    }
public Event playTime(String playTime) {
        this.playTime=playTime;
        return this;
    }

    public String getSceneType() {
        return sceneType;
    }

    public String getRecRequestId() {
        return recRequestId;
    }

    public String getPlayTime() {
        return playTime;
    }
}