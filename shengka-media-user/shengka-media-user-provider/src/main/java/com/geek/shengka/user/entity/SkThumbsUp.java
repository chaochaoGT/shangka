package com.geek.shengka.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_thumbs_up
 * @author 
 */
public class SkThumbsUp implements Serializable {
	
    public static final Byte thumbType_voice=1;
    
    public static final Byte thumbType_video=2;
	
    /**
     * 主键
     */
    private Long id;

    /**
     * 点赞用户id
     */
    private Long userId;

    /**
     * 点赞类型（1-语音  2-视频）
     */
    private Byte thumbType;

    /**
     * 点赞资源id
     */
    private String mediaId;

    /**
     * 创建时间
     */
    private Date createTime;

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

    public Byte getThumbType() {
        return thumbType;
    }

    public void setThumbType(Byte thumbType) {
        this.thumbType = thumbType;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        SkThumbsUp other = (SkThumbsUp) that;
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
        sb.append(", thumbType=").append(thumbType);
        sb.append(", mediaId=").append(mediaId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}