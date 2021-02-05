package com.geek.shengka.user.entity.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * sk_thumbs_up_notice
 * @author 
 */
public class SkThumbsUpNoticeVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1372654286575496687L;

	/**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 点赞id
     */
    private Long thumbsId;

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
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 通知内容
     */
    private String noticeContent;

    /**
     * 视频url
     */
    private String videoUrl;

    /**
     * 语音url
     */
    private String voiceUrl;
    
    /**
     * 用户昵称
     */
    private String nickName;
    
    /**
     * 语音时长
     */
    private Integer voiceDuration;
    
    /**
     * 用户头像
     */
    private String userIcon;


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

    public Long getThumbsId() {
        return thumbsId;
    }

    public void setThumbsId(Long thumbsId) {
        this.thumbsId = thumbsId;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getVoiceDuration() {
		return voiceDuration;
	}

	public void setVoiceDuration(Integer voiceDuration) {
		this.voiceDuration = voiceDuration;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
    
    
}