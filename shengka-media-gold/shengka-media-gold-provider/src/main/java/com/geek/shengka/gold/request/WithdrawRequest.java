package com.geek.shengka.gold.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.*;

/**
 * 
 * 提现请求VO
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:,date:2019-05-20 11:05:48, </p>
 * @author 
 * @date 2019-05-20 11:05:48
 * @since 
 * @version 
 *
 */

@Data
public class WithdrawRequest{
    
    /**账户类型 0-支付宝，1-微信 **/
    @NotNull
    private Integer withdrawAccountType;
    /**账户**/
    @NotNull
    private String withdrawAccount;
    /**账户名**/
    @NotNull
    private String withdrawAccountName;
    /**提现金额**/
    @NotNull
    private BigDecimal withdrawAmount;

}
