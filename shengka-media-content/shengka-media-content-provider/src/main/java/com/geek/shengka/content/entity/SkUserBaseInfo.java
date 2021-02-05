package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_user_base_info
 * @author 
 */
public class SkUserBaseInfo implements Serializable {
    private static final long serialVersionUID = 7351499741633226622L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 声咖号
     */
    private String skCode;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 背景图片
     */
    private String background;

    /**
     * 获赞数量
     */
    private Integer thumbsNum;

    /**
     * 订阅话题数量
     */
    private Integer subscribeTopicNum;

    /**
     * 发布作品数量
     */
    private Integer publishNum;

    /**
     * 发声数量
     */
    private Integer voiceNum;

    /**
     * 粉丝数量
     */
    private Integer fansNum;

    /**
     * 被喜欢数量
     */
    private Integer likedNum;

    /**
     * 最近浏览视频数量
     */
    private Integer nearestNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String userIcon;

    /**
     * 我关注的用户数量
     */
    private Integer attentionNum;

    /**
     * 我喜欢的作品数量
     */
    private Integer likeWorksNum;
    /**
     * 性别 1：男，0：女，2：未知
     */
    private Integer gender;

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

    public String getSkCode() {
        return skCode;
    }

    public void setSkCode(String skCode) {
        this.skCode = skCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Integer getThumbsNum() {
        return thumbsNum;
    }

    public void setThumbsNum(Integer thumbsNum) {
        this.thumbsNum = thumbsNum;
    }

    public Integer getSubscribeTopicNum() {
        return subscribeTopicNum;
    }

    public void setSubscribeTopicNum(Integer subscribeTopicNum) {
        this.subscribeTopicNum = subscribeTopicNum;
    }

    public Integer getPublishNum() {
        return publishNum;
    }

    public void setPublishNum(Integer publishNum) {
        this.publishNum = publishNum;
    }

    public Integer getVoiceNum() {
        return voiceNum;
    }

    public void setVoiceNum(Integer voiceNum) {
        this.voiceNum = voiceNum;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public Integer getLikedNum() {
        return likedNum;
    }

    public void setLikedNum(Integer likedNum) {
        this.likedNum = likedNum;
    }

    public Integer getNearestNum() {
        return nearestNum;
    }

    public void setNearestNum(Integer nearestNum) {
        this.nearestNum = nearestNum;
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

    public Integer getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(Integer attentionNum) {
        this.attentionNum = attentionNum;
    }

    public Integer getLikeWorksNum() {
        return likeWorksNum;
    }

    public void setLikeWorksNum(Integer likeWorksNum) {
        this.likeWorksNum = likeWorksNum;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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
        SkUserBaseInfo other = (SkUserBaseInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getSkCode() == null ? other.getSkCode() == null : this.getSkCode().equals(other.getSkCode()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getBackground() == null ? other.getBackground() == null : this.getBackground().equals(other.getBackground()))
            && (this.getThumbsNum() == null ? other.getThumbsNum() == null : this.getThumbsNum().equals(other.getThumbsNum()))
            && (this.getSubscribeTopicNum() == null ? other.getSubscribeTopicNum() == null : this.getSubscribeTopicNum().equals(other.getSubscribeTopicNum()))
            && (this.getPublishNum() == null ? other.getPublishNum() == null : this.getPublishNum().equals(other.getPublishNum()))
            && (this.getVoiceNum() == null ? other.getVoiceNum() == null : this.getVoiceNum().equals(other.getVoiceNum()))
            && (this.getFansNum() == null ? other.getFansNum() == null : this.getFansNum().equals(other.getFansNum()))
            && (this.getLikedNum() == null ? other.getLikedNum() == null : this.getLikedNum().equals(other.getLikedNum()))
            && (this.getNearestNum() == null ? other.getNearestNum() == null : this.getNearestNum().equals(other.getNearestNum()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSkCode() == null) ? 0 : getSkCode().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getBackground() == null) ? 0 : getBackground().hashCode());
        result = prime * result + ((getThumbsNum() == null) ? 0 : getThumbsNum().hashCode());
        result = prime * result + ((getSubscribeTopicNum() == null) ? 0 : getSubscribeTopicNum().hashCode());
        result = prime * result + ((getPublishNum() == null) ? 0 : getPublishNum().hashCode());
        result = prime * result + ((getVoiceNum() == null) ? 0 : getVoiceNum().hashCode());
        result = prime * result + ((getFansNum() == null) ? 0 : getFansNum().hashCode());
        result = prime * result + ((getLikedNum() == null) ? 0 : getLikedNum().hashCode());
        result = prime * result + ((getNearestNum() == null) ? 0 : getNearestNum().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
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
        sb.append(", skCode=").append(skCode);
        sb.append(", remark=").append(remark);
        sb.append(", background=").append(background);
        sb.append(", thumbsNum=").append(thumbsNum);
        sb.append(", subscribeTopicNum=").append(subscribeTopicNum);
        sb.append(", publishNum=").append(publishNum);
        sb.append(", voiceNum=").append(voiceNum);
        sb.append(", fansNum=").append(fansNum);
        sb.append(", likedNum=").append(likedNum);
        sb.append(", nearestNum=").append(nearestNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}