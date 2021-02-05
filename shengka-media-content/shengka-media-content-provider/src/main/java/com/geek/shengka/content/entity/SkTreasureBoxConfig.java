package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_treasure_box_config
 * @author 
 */
public class SkTreasureBoxConfig implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 宝箱活动名称
     */
    private String boxName;

    /**
     * 活动简介
     */
    private String content;

    /**
     * 累计时长（单位：秒）
     */
    private Integer watchDuration;

    /**
     * 宝箱金币下限
     */
    private Integer coinMin;

    /**
     * 宝箱金币上限
     */
    private Integer coinMax;

    /**
     * 图标url
     */
    private String iconUrl;

    /**
     * 每日领取次数上限
     */
    private Integer frequency;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     *  0-启用,1-禁用
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

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getWatchDuration() {
        return watchDuration;
    }

    public void setWatchDuration(Integer watchDuration) {
        this.watchDuration = watchDuration;
    }

    public Integer getCoinMin() {
        return coinMin;
    }

    public void setCoinMin(Integer coinMin) {
        this.coinMin = coinMin;
    }

    public Integer getCoinMax() {
        return coinMax;
    }

    public void setCoinMax(Integer coinMax) {
        this.coinMax = coinMax;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", boxName=").append(boxName);
        sb.append(", content=").append(content);
        sb.append(", watchDuration=").append(watchDuration);
        sb.append(", coinMin=").append(coinMin);
        sb.append(", coinMax=").append(coinMax);
        sb.append(", iconUrl=").append(iconUrl);
        sb.append(", frequency=").append(frequency);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", enable=").append(enable);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}