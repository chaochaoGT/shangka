package com.geek.shengka.gold.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 未领取宝箱对象返回
 * @author 
 */
@Getter
@Setter
public class UserTreasureBoxResponse implements Serializable {
    /**
     * 宝箱记录ID
     */
    private Long recordId;

    /**
     * 赢取金币数量
     */
    private BigDecimal goldCoinWin;


}