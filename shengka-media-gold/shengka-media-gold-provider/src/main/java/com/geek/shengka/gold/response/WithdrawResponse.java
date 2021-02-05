package com.geek.shengka.gold.response;

import java.math.BigDecimal;

import lombok.*;

/**
 * 
 * 提现请求VO
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:,date:2019-05-20 11:05:48,
 * </p>
 * 
 * @author
 * @date 2019-05-20 11:05:48
 * @since
 * @version
 *
 */

@Data
public class WithdrawResponse {

	/** 手续费 **/
	private String serviceFee;
	/** 提现金额 **/
	private BigDecimal withdrawAmount;
	/** 到账账户 **/
	private String withdrawAccount;
	/** 到账账户 名 **/
	private String withdrawAccountName;
	/** 到账状态0-申请，1-处理中，2-已到账 **/
	private int status;
	/** 身份证是否绑定0-已绑定，1-未到账 **/
	private int cardStatus;
	/** 可有效提现金额 **/
	private BigDecimal userAmount;
	/** 账务中心返回码 **/
	private int accountCode;
	/** 身份证是否绑定**/
	private String idCard;
	/** 可用免税额度 **/
	private BigDecimal taxExemptionAmount;
}
