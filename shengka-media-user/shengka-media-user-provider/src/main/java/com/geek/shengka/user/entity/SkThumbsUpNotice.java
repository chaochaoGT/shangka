package com.geek.shengka.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_thumbs_up_notice
 * @author 
 */
public class SkThumbsUpNotice implements Serializable {
	
	
	public static final Byte noticeState_notLook=0;

	
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String userIcon;

    /**
     * 点赞id
     */
    private Long thumbsId;

    /**
     * 被通知用户id(被赞作品的作者)
     */
    private Long noticeUserId;

    /**
     * 赞类型（1-赞视频  2-赞语音）
     */
    private Byte thumbType;

    /**
     * 视频id(被赞视频 或者  被赞语音评论的视频)
     */
    private String videoId;

    /**
     * 被赞语音id
     */
    private String voiceId;

    /**
     * 消息状态（0-未查看  1-已查看   2-已删除）
     */
    private Byte noticeState;

    /**
     * 通知内容
     */
    private String noticeContent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 视频封面url
     */
    private String videoUrl;

    /**
     * 语音url
     */
    private String voiceUrl;

    /**
     * 语音时长
     */
    private Integer voiceDuration;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public Long getThumbsId() {
        return thumbsId;
    }

    public void setThumbsId(Long thumbsId) {
        this.thumbsId = thumbsId;
    }

    public Long getNoticeUserId() {
        return noticeUserId;
    }

    public void setNoticeUserId(Long noticeUserId) {
        this.noticeUserId = noticeUserId;
    }

    public Byte getThumbType() {
        return thumbType;
    }

    public void setThumbType(Byte thumbType) {
        this.thumbType = thumbType;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVoiceId() {
        return voiceId;
    }

    public void setVoiceId(String voiceId) {
        this.voiceId = voiceId;
    }

    public Byte getNoticeState() {
        return noticeState;
    }

    public void setNoticeState(Byte noticeState) {
        this.noticeState = noticeState;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public Integer getVoiceDuration() {
        return voiceDuration;
    }

    public void setVoiceDuration(Integer voiceDuration) {
        this.voiceDuration = voiceDuration;
    }
}