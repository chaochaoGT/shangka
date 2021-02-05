package com.geek.shengka.gold.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseBeanCode;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.common.http.ContextTools;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.gold.constant.RedisKeyConstant;
import com.geek.shengka.gold.proxy.AccountingCenterProxy;
import com.geek.shengka.gold.request.BindingIdentityRequest;
import com.geek.shengka.gold.request.UserFlowRequest;
import com.geek.shengka.gold.request.UserTaskRecordRequest;
import com.geek.shengka.gold.response.UserAccountVo;
import com.geek.shengka.gold.response.UserFlowVo;
import com.geek.shengka.gold.service.SkUserAccountService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户账户信息服务
 *
 * @author: yunfei
 * @create: 2019-06-03 14:32
 **/
@RestController
@RequestMapping("/v1/account")
public class SkUserAccountController {

    @Autowired
    private AccountingCenterProxy accountingCenterProxy;
    @Autowired
    private SkUserAccountService skUserAccountService;
    // 失效时间60分钟
    private long EXPIRE_TIME = 5 * 60;
    /**
     * 获取用户账户流水
     *
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/flowList")
    public BaseResponse getUserFlowList(UserFlowRequest params) {
        params.setUserId(UserContextHolder.getUserId());
        if (0 >= params.getUserId()) {
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(), ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        List<UserFlowVo> flowList = accountingCenterProxy.getUserFlowList(params);
        return BaseResponse.newSuccess(flowList);
    }


    /**
     * 每日任务领取金币接口
     *
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/getGoldDailyTask")
    public BaseResponse getGoldDailyTask(@RequestBody @Valid UserTaskRecordRequest param) {
        if (param.getTaskRecordId()<=0) {
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(), ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        //领取宝箱，账户可能发生变动，清除流水缓存
         long userId = UserContextHolder.getUserIdByHeader();
         if (userId <= 0) {
             return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
         }
         CacheProvider.del(RedisKeyConstant.USER_ACCOUNT_INFO + userId);
    	skUserAccountService.getGoldDailyTask(param);
        return BaseResponse.newSuccess();
    }

    
    /**
     * 获取用户账户信息
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/info")
    public BaseResponse getUserAccount() throws Exception {
        String version = ContextTools.getRequest().getHeader("version");
        long userId = UserContextHolder.getUserIdByHeader();
        if (userId <= 0||StringUtils.isBlank(version)) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        UserAccountVo accountVo = CacheProvider.getObject(RedisKeyConstant.USER_ACCOUNT_INFO + userId, UserAccountVo.class);
        if (accountVo != null) {
            return BaseResponse.newSuccess(accountVo);
        }
        
        accountVo = skUserAccountService.getUserAccount(userId);
        CacheProvider.setObject(RedisKeyConstant.USER_ACCOUNT_INFO + userId, accountVo, EXPIRE_TIME);
        if(null!=accountVo) {
        	if(StringUtils.isBlank(accountVo.getApplyAllMoney())) {
        		accountVo.setApplyAllMoney("0");
        	}
        }
        
        return BaseResponse.newSuccess(accountVo);
    }
    
    
    /**
            * 绑定身份证
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/bingIdentityCard")
    public BaseResponse bingIdentityCard(@RequestBody BindingIdentityRequest parmas) {
        return skUserAccountService.bingIdentityCard(parmas);
    }

    
    
    /**
              *  活动提现，常规提现展示
     *
     * @return
     */
    @ResponseBody
    @IgnoreClientToken
    @GetMapping("/showWithdraws")
    public BaseResponse showWithdraws() {
        return BaseResponse.newSuccess(skUserAccountService.showWithdraws());
    }

    /**
	     *  活动提现，活动提现展示
	*
	* @return
	*/
	@ResponseBody
	@GetMapping("/showActivityWithdraws")
	public BaseResponse showActivityWithdraws() {
	return BaseResponse.newSuccess(skUserAccountService.showActivityWithdraws());
	}
    
    
}
