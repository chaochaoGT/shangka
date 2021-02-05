package com.geek.shengka.gold.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_task_config
 * @author 
 */
public class SkTaskConfig implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3550076636691329992L;
	
	public static final Byte TASKTYPE_DAILY=1 ;
	
	public static final Byte TASKTYPE_NEWMAN=2 ;	
	
	public static final Byte TRIGGEREVENT_PUBLISHVIDEO=1 ;
    
	public static final Byte TRIGGEREVENT_VOICECOMMENT=2 ;
    
	public static final Byte TRIGGEREVENT_ATTENTIONUSER=3 ;
    
	public static final Byte TRIGGEREVENT_LOGIN=4 ;
	
	public static final Byte AWARDAMOUNTTYPE_FIXED=1;
	
	public static final Byte AWARDAMOUNTTYPE_RANDOM=2;

	/**
     * 主键
     */
    private Long id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务简介
     */
    private String taskIntro;

    /**
     * 任务类型（1-每日任务  2-新手任务）
     */
    private Byte taskType;

    /**
     * 触发事件（1-发布视频  2-语音评论  3-关注用户  4-登录签到）
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
     * 任务状态（0-启用  1-禁用）
     */
    private Byte taskState;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskIntro() {
        return taskIntro;
    }

    public void setTaskIntro(String taskIntro) {
        this.taskIntro = taskIntro;
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

    public Byte getTaskState() {
        return taskState;
    }

    public void setTaskState(Byte taskState) {
        this.taskState = taskState;
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
        SkTaskConfig other = (SkTaskConfig) that;
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
        sb.append(", taskName=").append(taskName);
        sb.append(", taskIntro=").append(taskIntro);
        sb.append(", taskType=").append(taskType);
        sb.append(", triggerEvent=").append(triggerEvent);
        sb.append(", receiveLimit=").append(receiveLimit);
        sb.append(", awardAmountType=").append(awardAmountType);
        sb.append(", awardFixAmount=").append(awardFixAmount);
        sb.append(", awardMin=").append(awardMin);
        sb.append(", awardMax=").append(awardMax);
        sb.append(", taskState=").append(taskState);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}