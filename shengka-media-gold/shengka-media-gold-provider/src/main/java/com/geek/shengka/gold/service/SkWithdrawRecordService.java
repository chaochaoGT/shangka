package com.geek.shengka.gold.service;

import com.geek.shengka.gold.request.WithdrawRequest;
import com.geek.shengka.gold.response.DailyConfigResponse;
import com.geek.shengka.gold.response.WithdrawResponse;

import java.util.List;

/**
 * 提现
 * @author: xuxuelei
 * @create: 2019-08-02 15:06
 **/
public interface SkWithdrawRecordService {

     /**提现**/
	public WithdrawResponse withdraw(WithdrawRequest withdrawRequest);
}
