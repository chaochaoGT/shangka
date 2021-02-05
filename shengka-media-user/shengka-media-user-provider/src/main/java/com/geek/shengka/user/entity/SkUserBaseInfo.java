package com.geek.shengka.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_user_base_info
 *
 * @author
 */
@Data
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
}