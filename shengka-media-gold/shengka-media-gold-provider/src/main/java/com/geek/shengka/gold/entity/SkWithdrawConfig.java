package com.geek.shengka.gold.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * sk_withdraw_config
 * @author 
 */
public class SkWithdrawConfig implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 提现金额
     */
    private BigDecimal withdrawAmount;

    /**
     * 提现类型（1-常规提现  2-活动提现）
     */
    private Byte withdrawType;

    /**
     * 状态（0-启用  1-禁用）
     */
    private Byte enable;

    /**
     * 提现说明
     */
    private String withdrawIntro;

    /**
     * 活动提现解锁事件（1-连续登录天数  2-累计观看时长）
     */
    private Byte unlockEvent;

    /**
     * 连续登录天数
     */
    private Integer unlockLoginDay;

    /**
     * 累计观看时长
     */
    private Integer unlockWatchTime;

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

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public Byte getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(Byte withdrawType) {
        this.withdrawType = withdrawType;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    public String getWithdrawIntro() {
        return withdrawIntro;
    }

    public void setWithdrawIntro(String withdrawIntro) {
        this.withdrawIntro = withdrawIntro;
    }

    public Byte getUnlockEvent() {
        return unlockEvent;
    }

    public void setUnlockEvent(Byte unlockEvent) {
        this.unlockEvent = unlockEvent;
    }

    public Integer getUnlockLoginDay() {
        return unlockLoginDay;
    }

    public void setUnlockLoginDay(Integer unlockLoginDay) {
        this.unlockLoginDay = unlockLoginDay;
    }

    public Integer getUnlockWatchTime() {
        return unlockWatchTime;
    }

    public void setUnlockWatchTime(Integer unlockWatchTime) {
        this.unlockWatchTime = unlockWatchTime;
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
        SkWithdrawConfig other = (SkWithdrawConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWithdrawAmount() == null ? other.getWithdrawAmount() == null : this.getWithdrawAmount().equals(other.getWithdrawAmount()))
            && (this.getWithdrawType() == null ? other.getWithdrawType() == null : this.getWithdrawType().equals(other.getWithdrawType()))
            && (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
            && (this.getWithdrawIntro() == null ? other.getWithdrawIntro() == null : this.getWithdrawIntro().equals(other.getWithdrawIntro()))
            && (this.getUnlockEvent() == null ? other.getUnlockEvent() == null : this.getUnlockEvent().equals(other.getUnlockEvent()))
            && (this.getUnlockLoginDay() == null ? other.getUnlockLoginDay() == null : this.getUnlockLoginDay().equals(other.getUnlockLoginDay()))
            && (this.getUnlockWatchTime() == null ? other.getUnlockWatchTime() == null : this.getUnlockWatchTime().equals(other.getUnlockWatchTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWithdrawAmount() == null) ? 0 : getWithdrawAmount().hashCode());
        result = prime * result + ((getWithdrawType() == null) ? 0 : getWithdrawType().hashCode());
        result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
        result = prime * result + ((getWithdrawIntro() == null) ? 0 : getWithdrawIntro().hashCode());
        result = prime * result + ((getUnlockEvent() == null) ? 0 : getUnlockEvent().hashCode());
        result = prime * result + ((getUnlockLoginDay() == null) ? 0 : getUnlockLoginDay().hashCode());
        result = prime * result + ((getUnlockWatchTime() == null) ? 0 : getUnlockWatchTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
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
        sb.append(", withdrawAmount=").append(withdrawAmount);
        sb.append(", withdrawType=").append(withdrawType);
        sb.append(", enable=").append(enable);
        sb.append(", withdrawIntro=").append(withdrawIntro);
        sb.append(", unlockEvent=").append(unlockEvent);
        sb.append(", unlockLoginDay=").append(unlockLoginDay);
        sb.append(", unlockWatchTime=").append(unlockWatchTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}