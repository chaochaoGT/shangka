package com.geek.shengka.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_version
 * @author 
 */
public class SkVersion implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * APP类型：1-iPhone 2-iPad 3-Android 4-微信 5-H5
     */
    private Byte appType;

    /**
     * 0:常规更新     1：强制更新
     */
    private Byte forcedUpdate;

    /**
     * 版本序号，递增
     */
    private Integer versionCode;

    /**
     * 版本号
     */
    private String versionNumber;

    /**
     * 下载地址
     */
    private String downloadUrl;

    /**
     * 状态: 0 失效  1 有效  9 已删除  
     */
    private Byte state;

    /**
     * 是否弹窗  1-弹窗   2-不弹窗
     */
    private Byte popup;

    /**
     * 更新开始时间
     */
    private Date beginTime;

    /**
     * 更新结束时间
     */
    private Date endTime;

    /**
     * 产品类型
     */
    private String prdType;

    /**
     * 渠道id
     */
    private Long channelId;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 备注
     */
    private String remark;

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
     * 更新描述
     */
    private String changeDesc;

    /**
     * 更新日志
     */
    private String changeLog;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getAppType() {
        return appType;
    }

    public void setAppType(Byte appType) {
        this.appType = appType;
    }

    public Byte getForcedUpdate() {
        return forcedUpdate;
    }

    public void setForcedUpdate(Byte forcedUpdate) {
        this.forcedUpdate = forcedUpdate;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getPopup() {
        return popup;
    }

    public void setPopup(Byte popup) {
        this.popup = popup;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPrdType() {
        return prdType;
    }

    public void setPrdType(String prdType) {
        this.prdType = prdType;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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

    public String getChangeDesc() {
        return changeDesc;
    }

    public void setChangeDesc(String changeDesc) {
        this.changeDesc = changeDesc;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

}