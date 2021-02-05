package com.geek.shengka.user.entity.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * sk_comment_notice
 * @author 
 */
public class SkCommentNoticeVo implements Serializable {
	
	
	public static final Byte noticeState_notLook=0;
	
	
	public static final  Byte noticeType_publishVoiceComment=1;
	
	public static final  Byte noticeType_publishVideoAt=2;
	
	public static final  Byte noticeType_publishVoiceAt=3;
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 被@通知用户id
     */
    private Long noticeUserId;

    /**
     * 视频id
     */
    private String videoId;

    /**
     * 语音id
     */
    private String voiceId;

    /**
     * 通知类型（1-用户发语音评论视频  2-用户发视频@被通知人  3-用户语音@被通知人）
     */
    private Byte noticeType;

    /**
     * 通知状态（0-未查看  1-已查看  2-已删除）
     */
    private Byte noticeState;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 通知内容
     */
    private String noticeContent;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String userIcon;

    /**
     * 视频url
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

    public Long getNoticeUserId() {
        return noticeUserId;
    }

    public void setNoticeUserId(Long noticeUserId) {
        this.noticeUserId = noticeUserId;
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

    public Byte getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Byte noticeType) {
        this.noticeType = noticeType;
    }

    public Byte getNoticeState() {
        return noticeState;
    }

    public void setNoticeState(Byte noticeState) {
        this.noticeState = noticeState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
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