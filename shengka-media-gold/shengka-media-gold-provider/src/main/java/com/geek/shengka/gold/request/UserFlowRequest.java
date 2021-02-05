package com.geek.shengka.gold.request;

import lombok.Getter;
import lombok.Setter;


/**
 * 用户流水入参
 *
 * @author: yunfei
 * @create: 2019-06-03 15:00
 **/
@Getter
@Setter
public class UserFlowRequest {

    /**用户id*/
    private Long userId;

    /**每页多少条*/
    private int pageSize=10;

    /**页码*/
    private int pageNum=1;

    /**
     * 账户类型 0是人民币（提现明细），1是金币（金币明细）
     */
    private int accountType;
}
