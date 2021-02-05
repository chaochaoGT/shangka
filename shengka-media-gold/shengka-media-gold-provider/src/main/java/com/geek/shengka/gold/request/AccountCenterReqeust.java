package com.geek.shengka.gold.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交互 账务中心入参
 *
 * @author: yunfei
 * @create: 2019-06-06 10:15
 **/
@Data
@Builder
public class AccountCenterReqeust {

    /**用户id*/
    private long userId;
    /**交易类型 1001: “提现”，1002: “用户收入金币”，1003: “用户支出金币”*/
    private int tradeTypeCode;
    /**交易量*/
    private BigDecimal tradeAmount;
    /**交易申请时间*/
    private Date applyTime;
    /**业务线申请code*/
    private String bizApplyCode;
    /**回调url*/
    private String callbackUrl;
    /**回调时原样返回*/
    private String ext;

    /**秘钥*/
    private String md5;
    /**用户支付账号*/
    private String userPayAccount;
    /**用户支付账号绑定人名*/
    private String userPayName;


}
