package com.geek.shengka.gold.response;

import lombok.Data;

import java.util.Date;

/**
 * 用户账户信息
 *
 * @author: yunfei
 * @create: 2019-06-03 14:47
 **/
@Data
public class UserFlowVo {

    /**收支类型(1-收入，2-支出)*/
    private int budgetType;

    /**交易额度*/
    private double tradeAmount;

    /**交易类型名称（提现，签到，发布等）*/
    private String tradeType;

    /**交易时间*/
    private Date tradeTime;

    /**描述*/
    private String tradeDesc;

    /**状态 1.成功  2.失败*/
    private Integer states;
}
