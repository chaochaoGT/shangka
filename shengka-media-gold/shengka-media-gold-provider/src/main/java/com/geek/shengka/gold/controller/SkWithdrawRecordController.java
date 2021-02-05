package com.geek.shengka.gold.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseBeanCode;
import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.gold.constant.RedisKeyConstant;
import com.geek.shengka.gold.request.WithdrawRequest;
import com.geek.shengka.gold.response.WithdrawResponse;
import com.geek.shengka.gold.service.SkWithdrawRecordService;

/**
 * 提现请求
 * @author  xuxuelei
 * @date 2019-3-6
 */
@RestController
@RequestMapping("/v1/gold")
@IgnoreClientToken
public class SkWithdrawRecordController{
	@Autowired
	private SkWithdrawRecordService skWithdrawRecordService;
   
    @PostMapping("/withdraw")
	public BaseResponse<WithdrawResponse> withdraw(@RequestBody @Valid WithdrawRequest withdrawRequest) {
        Long userId = UserContextHolder.getUserIdByHeader();
        if (0 >= userId) {
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(), ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        
        try {
            CacheProvider.del(RedisKeyConstant.USER_FLOW_INFO + UserContextHolder.getUserIdByHeader());
            CacheProvider.del(RedisKeyConstant.USER_ACCOUNT_INFO + UserContextHolder.getUserIdByHeader());
        } catch (Exception e) {
        }
        //添加3秒内只能操作一次提现现在
        String userLocal = CacheProvider.get(RedisKeyConstant.USER_WITHDRAW_RECORD + UserContextHolder.getUserIdByHeader());
        if(StringUtils.isNotBlank(userLocal)){
            return BaseResponse.newFailure(ResponseBeanCode.SYSTEM_ERROR.getCode(), "不可频繁操作");
        }
        CacheProvider.set(RedisKeyConstant.USER_WITHDRAW_RECORD + UserContextHolder.getUserIdByHeader(),"1",3);
        return BaseResponse.newSuccess(skWithdrawRecordService.withdraw(withdrawRequest));
	}
 
 

}

