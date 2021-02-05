package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * sk_withdraw_record
 * @author 
 */
public class SkWithdrawRecord implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 申请订单号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 提现零钱金额
     */
    private BigDecimal withdrawAmount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 实际到账金额
     */
    private BigDecimal money;

    /**
     * 账户变动流水号（账务中心返回）
     */
    private String tradeNo;

    /**
     * 处理状态（0-待处理   1-处理成功   2-处理失败）
     */
    private Byte handleState;

    /**
     * 处理备注（比如失败原因）
     */
    private String handleRemark;

    /**
     * 手续费
     */
    private BigDecimal serviceFee;

    /**
     * 提现账户
     */
    private String withdrawAccount;

    /**
     * 提现账号类型（1-支付宝  2-微信）
     */
    private Byte withdrawAccountType;

    /**
     * 提现账户名
     */
    private String withdrawAccountName;

    /**
     * 并发占用标识
     */
    private String scychronizeFlag;

    /**
     * 处理次数
     */
    private Integer processCts;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Byte getHandleState() {
        return handleState;
    }

    public void setHandleState(Byte handleState) {
        this.handleState = handleState;
    }

    public String getHandleRemark() {
        return handleRemark;
    }

    public void setHandleRemark(String handleRemark) {
        this.handleRemark = handleRemark;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getWithdrawAccount() {
        return withdrawAccount;
    }

    public void setWithdrawAccount(String withdrawAccount) {
        this.withdrawAccount = withdrawAccount;
    }

    public Byte getWithdrawAccountType() {
        return withdrawAccountType;
    }

    public void setWithdrawAccountType(Byte withdrawAccountType) {
        this.withdrawAccountType = withdrawAccountType;
    }

    public String getWithdrawAccountName() {
        return withdrawAccountName;
    }

    public void setWithdrawAccountName(String withdrawAccountName) {
        this.withdrawAccountName = withdrawAccountName;
    }

    public String getScychronizeFlag() {
        return scychronizeFlag;
    }

    public void setScychronizeFlag(String scychronizeFlag) {
        this.scychronizeFlag = scychronizeFlag;
    }

    public Integer getProcessCts() {
        return processCts;
    }

    public void setProcessCts(Integer processCts) {
        this.processCts = processCts;
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
        SkWithdrawRecord other = (SkWithdrawRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getWithdrawAmount() == null ? other.getWithdrawAmount() == null : this.getWithdrawAmount().equals(other.getWithdrawAmount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getHandleState() == null ? other.getHandleState() == null : this.getHandleState().equals(other.getHandleState()))
            && (this.getHandleRemark() == null ? other.getHandleRemark() == null : this.getHandleRemark().equals(other.getHandleRemark()))
            && (this.getServiceFee() == null ? other.getServiceFee() == null : this.getServiceFee().equals(other.getServiceFee()))
            && (this.getWithdrawAccount() == null ? other.getWithdrawAccount() == null : this.getWithdrawAccount().equals(other.getWithdrawAccount()))
            && (this.getWithdrawAccountType() == null ? other.getWithdrawAccountType() == null : this.getWithdrawAccountType().equals(other.getWithdrawAccountType()))
            && (this.getWithdrawAccountName() == null ? other.getWithdrawAccountName() == null : this.getWithdrawAccountName().equals(other.getWithdrawAccountName()))
            && (this.getScychronizeFlag() == null ? other.getScychronizeFlag() == null : this.getScychronizeFlag().equals(other.getScychronizeFlag()))
            && (this.getProcessCts() == null ? other.getProcessCts() == null : this.getProcessCts().equals(other.getProcessCts()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getWithdrawAmount() == null) ? 0 : getWithdrawAmount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getHandleState() == null) ? 0 : getHandleState().hashCode());
        result = prime * result + ((getHandleRemark() == null) ? 0 : getHandleRemark().hashCode());
        result = prime * result + ((getServiceFee() == null) ? 0 : getServiceFee().hashCode());
        result = prime * result + ((getWithdrawAccount() == null) ? 0 : getWithdrawAccount().hashCode());
        result = prime * result + ((getWithdrawAccountType() == null) ? 0 : getWithdrawAccountType().hashCode());
        result = prime * result + ((getWithdrawAccountName() == null) ? 0 : getWithdrawAccountName().hashCode());
        result = prime * result + ((getScychronizeFlag() == null) ? 0 : getScychronizeFlag().hashCode());
        result = prime * result + ((getProcessCts() == null) ? 0 : getProcessCts().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", userId=").append(userId);
        sb.append(", withdrawAmount=").append(withdrawAmount);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", money=").append(money);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", handleState=").append(handleState);
        sb.append(", handleRemark=").append(handleRemark);
        sb.append(", serviceFee=").append(serviceFee);
        sb.append(", withdrawAccount=").append(withdrawAccount);
        sb.append(", withdrawAccountType=").append(withdrawAccountType);
        sb.append(", withdrawAccountName=").append(withdrawAccountName);
        sb.append(", scychronizeFlag=").append(scychronizeFlag);
        sb.append(", processCts=").append(processCts);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}