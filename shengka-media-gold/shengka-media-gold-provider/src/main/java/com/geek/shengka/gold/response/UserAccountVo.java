package com.geek.shengka.gold.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * 用户账户信息
 *
 * @author: yunfei
 * @create: 2019-06-03 14:47
 **/
@Data
public class UserAccountVo {

    /**
     * 今日浏览收入
     */
    private String goldCoinsPerDay;

    /**
     * 总金币数
     */
    private String totalGoldCoins;

    /**
     * 昨日收入金额
     */
    private String yesterdayAmount;

    /**
     * 零钱总金额
     */
    private String totalAmount;

    /**
     * 当月剩余免税额度
     */
    private String dutyFreeAmount;

    /**
     * 汇率：金币
     */
    private String goldCoinsScale;

    /**
     * 汇率：人民币数
     */
    private String amountScale;

    /**
     * 身份认证（1为已绑身份证2为未绑定身份）
     */
    private String identityAttestation;

    /**
     * 活跃度
     */
    private int activeNumber;

    /**
     * 总活跃度
     */
    private int activeMax;
    
    /** 历史兑换 **/
    private String applyAllMoney;

 
}
