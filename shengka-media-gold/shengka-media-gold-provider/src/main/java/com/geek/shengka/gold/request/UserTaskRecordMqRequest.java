package com.geek.shengka.gold.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 每日任务领取金币
 * @author: xuxuelei
 * @create: 2019-06-04 17:29
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserTaskRecordMqRequest implements Serializable {

    /**用户id**/
    private Long userId;

    /**金币数量**/
    private BigDecimal goldCoin;

    /**业务订单号**/
    private String orderNo;

    /**交易类型  取枚举  TradeTypeEnum*/
    private int tradeTypeCode;
    
    /**回调时原样返回**/
    private String ext;

}
