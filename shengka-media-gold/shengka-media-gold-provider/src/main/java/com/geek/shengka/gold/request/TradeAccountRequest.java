package com.geek.shengka.gold.request;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import lombok.*;

/**
 * 
 * 回调接口请求VO
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:,date:2019-05-20 11:05:48, </p>
 * @author 
 * @date 2019-05-20 11:05:48
 * @since 
 * @version 
 *
 */

@Data
public class TradeAccountRequest {
   
	private long userId;
	private int accountType;
	private String bizCode;

}
