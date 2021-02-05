package com.geek.shengka.gold.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 每日任务配置返回对象
 *
 * @author: yunfei
 * @create: 2019-08-02 15:03
 **/
@Getter
@Setter
public class DailyConfigResponse {

    /**
     * 主键
     */
    private Long taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务简介
     */
    private String taskIntro;

    /**
     * 每日任务每日领取次数限制
     */
    private Integer receiveLimit;

    /**
     * 每日完成任务已领取次数
     */
    private int getNum;
    /**
     * 每日完成任务未领取次数
     */
    private Integer notGetNum;

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
     * 任务状态，0 去做任务，1 可领取，2 已完成
     */
    private int taskStatus=0;

}
