package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qubianzhong
 * @Date 16:38 2019/8/2
 */
@Data
@ApiModel(value = "任务配置")
public class SkTaskConfig implements Serializable {
    private static final long serialVersionUID = 956920581974614463L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    private String taskName;

    /**
     * 任务简介
     */
    @ApiModelProperty(value = "任务简介")
    private String taskIntro;

    /**
     * 任务类型（1-每日任务  2-新手任务）
     */
    @ApiModelProperty(value = "任务类型（1-每日任务  2-新手任务）", allowableValues = "1,2")
    private Byte taskType;

    /**
     * 触发事件（1-发布视频  2-语音评论  3-关注用户  4-登录签到   5-首发语音评论）
     */
    @ApiModelProperty(value = "触发事件（1-发布视频  2-语音评论  3-关注用户  4-登录签到   5-首发语音评论）"
            , allowableValues = "1,2,3,4,5")
    private Byte triggerEvent;

    /**
     * 每日任务每日领取次数限制
     */
    @ApiModelProperty(value = "每日任务每日领取次数限制")
    private Integer receiveLimit;

    /**
     * 奖励额度类型（1-固定额度  2-随机额度）
     */
    @ApiModelProperty(value = "奖励额度类型（1-固定额度  2-随机额度）", allowableValues = "1,2")
    private Byte awardAmountType;

    /**
     * 奖励固定额度
     */
    @ApiModelProperty(value = "奖励固定额度")
    private Integer awardFixAmount;

    /**
     * 奖励额下限
     */
    @ApiModelProperty(value = "奖励额下限")
    private Integer awardMin;

    /**
     * 奖励额上限
     */
    @ApiModelProperty(value = "奖励额上限")
    private Integer awardMax;

    /**
     * 任务状态（0-启用  1-禁用）
     */
    @ApiModelProperty(value = "任务状态（0-启用  1-禁用）", allowableValues = "0,1")
    private Byte taskState;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

}