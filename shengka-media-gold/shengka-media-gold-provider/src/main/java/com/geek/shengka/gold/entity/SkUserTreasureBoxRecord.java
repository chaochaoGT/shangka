package com.geek.shengka.gold.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * sk_user_treasure_box_record
 * @author 
 */
public class SkUserTreasureBoxRecord implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 宝箱id
     */
    private String treasureId;

    /**
     * 赢取金币数量
     */
    private BigDecimal goldCoinWin;

    /**
     * 0-未领取，1-已领取
     */
    private Byte state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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
     * 业务订单号
     */
    private String orderNo;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTreasureId() {
        return treasureId;
    }

    public void setTreasureId(String treasureId) {
        this.treasureId = treasureId;
    }

    public BigDecimal getGoldCoinWin() {
        return goldCoinWin;
    }

    public void setGoldCoinWin(BigDecimal goldCoinWin) {
        this.goldCoinWin = goldCoinWin;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", treasureId=").append(treasureId);
        sb.append(", goldCoinWin=").append(goldCoinWin);
        sb.append(", state=").append(state);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", handleState=").append(handleState);
        sb.append(", handleRemark=").append(handleRemark);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", scychronizeFlag=").append(scychronizeFlag);
        sb.append(", processCts=").append(processCts);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}