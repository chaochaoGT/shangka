package com.geek.shengka.gold.service;

import java.util.List;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.gold.entity.SkWithdrawConfig;
import com.geek.shengka.gold.request.BindingIdentityRequest;
import com.geek.shengka.gold.request.UserTaskRecordRequest;
import com.geek.shengka.gold.response.UserAccountVo;
import com.geek.shengka.gold.response.UserAccountWithDraw;

/**
 * 用户
 * @author: xuxuelei
 * @create: 2019-08-02 15:06
 **/
public interface SkUserAccountService {
    
	/** 每日金币获取 **/
	void getGoldDailyTask(UserTaskRecordRequest param);
	
    /**
     * 获取用户账户信息
     * @param userId
     * @return
     * @throws Exception
     */
    UserAccountVo getUserAccount(Long userId) throws Exception;


    /**
     * 绑定身份证
     * @param parmas
     * @return
     */
    BaseResponse bingIdentityCard(BindingIdentityRequest parmas);
    
    /**
     * 提现常规信息
     * @param parmas
     * @return
     */
    List<SkWithdrawConfig> showWithdraws();
	
    /**
     * 活动提现信息
     * @param parmas
     * @return
     */
    List<UserAccountWithDraw> showActivityWithdraws();
	
}
