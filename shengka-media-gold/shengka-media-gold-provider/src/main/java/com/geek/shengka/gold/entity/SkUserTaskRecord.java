package com.geek.shengka.gold.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_user_task_record
 * @author 
 */
public class SkUserTaskRecord implements Serializable {
	
	private static final long serialVersionUID = 8367881871946791737L;

	public static final Byte HANDLESTATE_WAITINGPROCESS=0;	//待处理
	
	public static final Byte RECEIVESTATE_NOT=0;	//未领取
	public static final Byte RECEIVESTATE_YES=1;	//已领取
	
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 任务类型（1-每日任务  2-新手任务）
     */
    private Byte taskType;

    /**
     * 触发事件（1-发布视频  2-语音评论  3-关注用户  4-登录签到   5-首发语音评论）
     */
    private Byte triggerEvent;

    /**
     * 每日任务每日领取次数限制
     */
    private Integer receiveLimit;

    /**
     * 奖励额度类型（1-固定额度  2-随机额度）
     */
    private Byte awardAmountType;

    /**
     * 奖励固定额度
     */
    private Integer awardFixAmount;

    /**
     * 奖励额下限
     */
    private Integer awardMin;

    /**
     * 奖励额上限
     */
    private Integer awardMax;

    /**
     * 获得奖励金币额
     */
    private Integer award;

    /**
     * 领取状态（0-未领取  1-已领取）
     */
    private Byte receiveState;

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
    
    private Integer completeCount;
    
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Byte getTaskType() {
        return taskType;
    }

    public void setTaskType(Byte taskType) {
        this.taskType = taskType;
    }

    public Byte getTriggerEvent() {
        return triggerEvent;
    }

    public void setTriggerEvent(Byte triggerEvent) {
        this.triggerEvent = triggerEvent;
    }

    public Integer getReceiveLimit() {
        return receiveLimit;
    }

    public void setReceiveLimit(Integer receiveLimit) {
        this.receiveLimit = receiveLimit;
    }

    public Byte getAwardAmountType() {
        return awardAmountType;
    }

    public void setAwardAmountType(Byte awardAmountType) {
        this.awardAmountType = awardAmountType;
    }

    public Integer getAwardFixAmount() {
        return awardFixAmount;
    }

    public void setAwardFixAmount(Integer awardFixAmount) {
        this.awardFixAmount = awardFixAmount;
    }

    public Integer getAwardMin() {
        return awardMin;
    }

    public void setAwardMin(Integer awardMin) {
        this.awardMin = awardMin;
    }

    public Integer getAwardMax() {
        return awardMax;
    }

    public void setAwardMax(Integer awardMax) {
        this.awardMax = awardMax;
    }

    public Integer getAward() {
        return award;
    }

    public void setAward(Integer award) {
        this.award = award;
    }

    public Byte getReceiveState() {
        return receiveState;
    }

    public void setReceiveState(Byte receiveState) {
        this.receiveState = receiveState;
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

    public Integer getCompleteCount() {
		return completeCount;
	}

	public void setCompleteCount(Integer completeCount) {
		this.completeCount = completeCount;
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
        SkUserTaskRecord other = (SkUserTaskRecord) that;
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
        sb.append(", taskId=").append(taskId);
        sb.append(", taskType=").append(taskType);
        sb.append(", triggerEvent=").append(triggerEvent);
        sb.append(", receiveLimit=").append(receiveLimit);
        sb.append(", awardAmountType=").append(awardAmountType);
        sb.append(", awardFixAmount=").append(awardFixAmount);
        sb.append(", awardMin=").append(awardMin);
        sb.append(", awardMax=").append(awardMax);
        sb.append(", award=").append(award);
        sb.append(", receiveState=").append(receiveState);
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