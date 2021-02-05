package com.geek.shengka.gold.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 看视频获取金币请求账务中心入参
 * @author: yunfei
 * @create: 2019-06-04 17:29
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MqCoinMsg implements Serializable {

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
