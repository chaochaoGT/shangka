package com.geek.shengka.user.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_voice
 * @author 
 */
public class SkVoiceVideoInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8865234644827746890L;

	/**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 视频id
     */
    private String videoId;

    /**
     * 语音地址
     */
    private String voiceUrl;

    /**
     * 播放时长（单位：秒）
     */
    private Integer duration;

    /**
     * 0-审核中，1-审核通过， 2-审核失败
     */
    private Byte enable;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    
    private String videoCoverImageUrl;
    
    private Long videoPublishUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getVideoPublishUserId() {
		return videoPublishUserId;
	}

	public void setVideoPublishUserId(Long videoPublishUserId) {
		this.videoPublishUserId = videoPublishUserId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SkVoiceVideoInfo other = (SkVoiceVideoInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", videoId=").append(videoId);
        sb.append(", voiceUrl=").append(voiceUrl);
        sb.append(", duration=").append(duration);
        sb.append(", enable=").append(enable);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public String getVideoCoverImageUrl() {
		return videoCoverImageUrl;
	}

	public void setVideoCoverImageUrl(String videoCoverImageUrl) {
		this.videoCoverImageUrl = videoCoverImageUrl;
	}
}