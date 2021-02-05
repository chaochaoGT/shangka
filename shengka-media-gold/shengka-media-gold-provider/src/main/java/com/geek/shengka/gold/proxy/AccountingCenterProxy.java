package com.geek.shengka.gold.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseBeanCode;
import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.common.enums.AccountTypeEnum;
import com.geek.shengka.common.proxy.AbstractProxy;
import com.geek.shengka.common.util.HttpTools;
import com.geek.shengka.common.util.Md5Utils;
import com.geek.shengka.gold.request.AccountBindingIdentityRequest;
import com.geek.shengka.gold.request.AccountCenterReqeust;
import com.geek.shengka.gold.request.AccountFlowRequest;
import com.geek.shengka.gold.request.AccountInfoRequest;
import com.geek.shengka.gold.request.BindingIdentityRequest;
import com.geek.shengka.gold.request.SyncCoinRequest;
import com.geek.shengka.gold.request.TradeAccountRequest;
import com.geek.shengka.gold.request.UserFlowRequest;
import com.geek.shengka.gold.response.UserAccountResponse;
import com.geek.shengka.gold.response.UserFlowVo;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 账务中心代码调用
 *
 * @author xuxuelei
 */
@ConfigurationProperties(prefix = "account.remote")
@Component
@Data
public class AccountingCenterProxy extends AbstractProxy {

    private static final Logger logger = LoggerFactory.getLogger(AccountingCenterProxy.class);

    /**
     * 账户中心ip端口
     */
    private String url;

    /**
     * 用户流水
     */
    private String userFlowMethod;

    /**
     * 用户账户信息
     */
    private String userAccountMethod;

    /**
     * 同步账户中心交易流水
     */
    private String syncGoldCoinFlowMethod;

    /**
     * 账户中心绑定身份证
     */
    private String bingIdentityCardMethod;

    /**
     * 查用户余额的方法
     */
    private String accountMethod;

    /**
     * 账户中心成功码
     */
    private static final int ACCOUNT_CENTER_CODE = 0;

    /**
     * 流水缓存
     */
    private static final String FLOW_USER_CACHE_KEY = "sk:userflow:userid-%s-page-%s-%s";
    // 默认失效时间：1小时
    private static final long EXPIRE_TIME = 5L * 60;

    /**
     * 获取用户账户信息
     *
     * @param userId
     * @return
     */
    public UserAccountResponse getUserAccount(long userId) throws Exception {
        try {
            BaseResponse baseResponse = postJsonCallAccount(this.url + userAccountMethod,
                    getUserAccountParams(userId, 0), BaseResponse.class);
            if (baseResponse.getCode() == ACCOUNT_CENTER_CODE) {
                UserAccountResponse userAccountVo = JSONObject.parseObject(JSON.toJSONString(baseResponse.getData()),
                        UserAccountResponse.class);
                return userAccountVo;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    
    /**
     * 获取账号参数
     *
     * @param userId
     * @param accountType
     * @return
     */
    private final String getUserAccountParams(long userId, int accountType) {
        List<Integer> integerList = getAccountType(0);
        integerList.addAll(getAccountType(1));
        return JSON.toJSONString(
                AccountInfoRequest.builder().userId(userId + "").business("1").accountTypeList(integerList).build());
    }

    /**
     * 获取账号类型
     *
     * @param accountType
     * @return
     */
    private List<Integer> getAccountType(int accountType) {
        List<Integer> list = new ArrayList<>();
        list.add(AccountTypeEnum.getType(accountType));
        return list;
    }
    
    
    /**
     * 获取用户流水
     *
     * @param params
     * @return
     */
    public List<UserFlowVo> getUserFlowList(UserFlowRequest params) {
        List<UserFlowVo> returnVal = new ArrayList<UserFlowVo>();
        try {
            AccountFlowRequest build = AccountFlowRequest.builder().userId(params.getUserId().toString())
                    .accountType(AccountTypeEnum.getType(params.getAccountType())).pageNo(params.getPageNum())
                    .pageSize(params.getPageSize()).build();

            String localKey = String.format(FLOW_USER_CACHE_KEY, params.getUserId(), params.getPageNum(), params.getPageSize());
            returnVal = CacheProvider.getObject(localKey, List.class);
            if (CollectionUtils.isNotEmpty(returnVal)) {
                return returnVal;
            } else {
                //调用账务中心
                BaseResponse<List<UserFlowVo>> baseResponse = postJsonCallAccount(this.url + userFlowMethod, JSON.toJSONString(build), BaseResponse.class);
                if (baseResponse.getCode() == ACCOUNT_CENTER_CODE) {
                    returnVal = baseResponse.getData();
                    CacheProvider.setObject(localKey, returnVal, EXPIRE_TIME);
                } else {
                    logger.error("account center call failed !! with response [" + JSON.toJSONString(baseResponse) + "]");
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return returnVal;
    }


    /**
     * 交易接口
     *
     * @param params
     * @return
     */
    public BaseResponse syncGoldCoinFlow(SyncCoinRequest params) {
        BaseResponse baseResponse = null;
        try {
            String timestamp = Clock.systemUTC().millis() + "";
            AccountCenterReqeust build = AccountCenterReqeust.builder()
                    .bizApplyCode(params.getOrderNo())
                    .applyTime(new Date())
                    .tradeTypeCode(params.getTradeTypeCode())
                    .tradeAmount(params.getGoldCoin())
                    .userId(params.getUserId())
                    .md5(Md5Utils.getMD5(params.getUserId() + params.getOrderNo() + timestamp + params.getGoldCoin().toString()))
                    .userPayAccount(params.getUserPayAccount())
                    .userPayName(params.getUserPayName())
                    .build();
            baseResponse = postJsonCallAccount(this.url + syncGoldCoinFlowMethod, JSON.toJSONString(build),
                    BaseResponse.class, timestamp);
        } catch (Exception e) {
            return BaseResponse.newFailure(ResponseBeanCode.SERVICE_NOT_AVALIABLE.getCode(), "账户中心异常");
        }
        return baseResponse==null?BaseResponse.newFailure(ResponseBeanCode.SERVICE_NOT_AVALIABLE.getCode(), "账户中心异常"):baseResponse;
    }

    
    /**
     * 查询对应账户信息接口
     *
     * @param tradeAccountRequest
     * @return
     */
    public BaseResponse remoteSelectAccount(TradeAccountRequest tradeAccountRequest) {
        tradeAccountRequest.setBizCode(LAOWANGSHIPIN);
        String jsonData = JSON.toJSONString(tradeAccountRequest);
        BaseResponse baseResponse = HttpTools.getForJson(this.url + accountMethod, jsonData, BaseResponse.class);
        return baseResponse;
    }
    
    
    /**
     * 绑定身份证
     *
     * @param params
     * @return
     */
    public BaseResponse bingIdentityCard(BindingIdentityRequest params) {
        AccountBindingIdentityRequest build = AccountBindingIdentityRequest.builder().bizCode(LAOWANGSHIPIN)
                .idCard(params.getIdentityNo()).userName(params.getUserName()).userId(params.getUserId() + "").build();
        BaseResponse baseResponse = postJsonCallAccount(this.url + bingIdentityCardMethod, JSON.toJSONString(build),
                BaseResponse.class);
        if (ACCOUNT_CENTER_CODE == baseResponse.getCode()) {
            return BaseResponse.newSuccess();
        }
        return BaseResponse.newFailure(baseResponse.getCode(), baseResponse.getMsg());
    }
    
}
