package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * sk_topic_config
 * @author 
 */
@Entity
@Table(name="sk_topic_config")
public class SkTopicConfig implements Serializable {
    /**
     * 主键
     */
	@Id
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 话题名称
     */
    private String topicName;

    /**
     * 话题简介
     */
    private String topicIntro;

    /**
     * 话题logo
     */
    private String topicLogo;

    /**
     * 发布视频数量
     */
    private Integer pushCount;

    /**
     * 话题类型（1-热门  2-推荐  3-新增）
     */
    private Byte topicTag;

    /**
     * 排序
     */
    private Integer seq;

    /**
     *  0-启用,1-禁用
     */
    private Byte enable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * (0-系统话题  1-用户自定义话题)
     */
    private Byte topicType;

    /**
     * 播放次数
     */
    private Integer watchCount;

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

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicIntro() {
        return topicIntro;
    }

    public void setTopicIntro(String topicIntro) {
        this.topicIntro = topicIntro;
    }

    public String getTopicLogo() {
        return topicLogo;
    }

    public void setTopicLogo(String topicLogo) {
        this.topicLogo = topicLogo;
    }

    public Integer getPushCount() {
        return pushCount;
    }

    public void setPushCount(Integer pushCount) {
        this.pushCount = pushCount;
    }

    public Byte getTopicTag() {
        return topicTag;
    }

    public void setTopicTag(Byte topicTag) {
        this.topicTag = topicTag;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Byte getTopicType() {
        return topicType;
    }

    public void setTopicType(Byte topicType) {
        this.topicType = topicType;
    }

    public Integer getWatchCount() {
        return watchCount;
    }

    public void setWatchCount(Integer watchCount) {
        this.watchCount = watchCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", topicName=").append(topicName);
        sb.append(", topicIntro=").append(topicIntro);
        sb.append(", topicLogo=").append(topicLogo);
        sb.append(", pushCount=").append(pushCount);
        sb.append(", topicTag=").append(topicTag);
        sb.append(", seq=").append(seq);
        sb.append(", enable=").append(enable);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", topicType=").append(topicType);
        sb.append(", watchCount=").append(watchCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}