package com.geek.shengka.gold.response;

import lombok.Data;

import java.util.Date;

/**
 * 用户提现活动账户信息
 *
 * @author: xuxuelei
 * @create: 2019-06-03 14:47
 **/
@Data
public class UserAccountWithDraw {
	
	private String withdrawAmount;
	private String unlockEvent;
	private int unlockLoginDay;
	private int unlockWatchTime;
   
}
