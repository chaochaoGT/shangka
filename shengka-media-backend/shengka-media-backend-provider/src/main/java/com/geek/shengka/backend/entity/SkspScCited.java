package com.geek.shengka.backend.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * llsp_sc_cited
 * @author 
 */
public class SkspScCited implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 系统（1 Android，2  H5）
     */
    private String osSystem;

    /**
     * 日期
     */
    private Date date;

    /**
     * 项目
     */
    private String app;

    /**
     * 渠道
     */
    private String market;

    /**
     * 代理商
     */
    private String agentAccount;

    /**
     * 渠道包名
     */
    private String marketName;

    /**
     * 账面消耗
     */
    private BigDecimal bookCac;

    /**
     * 现金消耗
     */
    private BigDecimal cashCac;

    /**
     * 曝光
     */
    private Long exposureCnt;

    /**
     * 点击数
     */
    private Long clickCnt;

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

    public String getOsSystem() {
        return osSystem;
    }

    public void setOsSystem(String osSystem) {
        this.osSystem = osSystem;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getAgentAccount() {
        return agentAccount;
    }

    public void setAgentAccount(String agentAccount) {
        this.agentAccount = agentAccount;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public BigDecimal getBookCac() {
        return bookCac;
    }

    public void setBookCac(BigDecimal bookCac) {
        this.bookCac = bookCac;
    }

    public BigDecimal getCashCac() {
        return cashCac;
    }

    public void setCashCac(BigDecimal cashCac) {
        this.cashCac = cashCac;
    }

    public Long getExposureCnt() {
        return exposureCnt;
    }

    public void setExposureCnt(Long exposureCnt) {
        this.exposureCnt = exposureCnt;
    }

    public Long getClickCnt() {
        return clickCnt;
    }

    public void setClickCnt(Long clickCnt) {
        this.clickCnt = clickCnt;
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
        SkspScCited other = (SkspScCited) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOsSystem() == null ? other.getOsSystem() == null : this.getOsSystem().equals(other.getOsSystem()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getApp() == null ? other.getApp() == null : this.getApp().equals(other.getApp()))
            && (this.getMarket() == null ? other.getMarket() == null : this.getMarket().equals(other.getMarket()))
            && (this.getAgentAccount() == null ? other.getAgentAccount() == null : this.getAgentAccount().equals(other.getAgentAccount()))
            && (this.getMarketName() == null ? other.getMarketName() == null : this.getMarketName().equals(other.getMarketName()))
            && (this.getBookCac() == null ? other.getBookCac() == null : this.getBookCac().equals(other.getBookCac()))
            && (this.getCashCac() == null ? other.getCashCac() == null : this.getCashCac().equals(other.getCashCac()))
            && (this.getExposureCnt() == null ? other.getExposureCnt() == null : this.getExposureCnt().equals(other.getExposureCnt()))
            && (this.getClickCnt() == null ? other.getClickCnt() == null : this.getClickCnt().equals(other.getClickCnt()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOsSystem() == null) ? 0 : getOsSystem().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getApp() == null) ? 0 : getApp().hashCode());
        result = prime * result + ((getMarket() == null) ? 0 : getMarket().hashCode());
        result = prime * result + ((getAgentAccount() == null) ? 0 : getAgentAccount().hashCode());
        result = prime * result + ((getMarketName() == null) ? 0 : getMarketName().hashCode());
        result = prime * result + ((getBookCac() == null) ? 0 : getBookCac().hashCode());
        result = prime * result + ((getCashCac() == null) ? 0 : getCashCac().hashCode());
        result = prime * result + ((getExposureCnt() == null) ? 0 : getExposureCnt().hashCode());
        result = prime * result + ((getClickCnt() == null) ? 0 : getClickCnt().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", osSystem=").append(osSystem);
        sb.append(", date=").append(date);
        sb.append(", app=").append(app);
        sb.append(", market=").append(market);
        sb.append(", agentAccount=").append(agentAccount);
        sb.append(", marketName=").append(marketName);
        sb.append(", bookCac=").append(bookCac);
        sb.append(", cashCac=").append(cashCac);
        sb.append(", exposureCnt=").append(exposureCnt);
        sb.append(", clickCnt=").append(clickCnt);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}