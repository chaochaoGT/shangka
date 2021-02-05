package com.geek.shengka.common.proxy;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.common.basemodel.UserTokenResponse;
import com.geek.shengka.common.enums.AuthServiceStatusEnum;
import com.geek.shengka.common.exception.auth.AuthClientApiException;
import com.geek.shengka.common.http.HttpPoolProxy;
import com.geek.shengka.common.request.UserInfoQueryRequest;
import com.geek.shengka.common.response.UserInfoResponse;
import com.geek.shengka.common.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


@Component
public class UserTokenHttpProxy {

    private static final Logger logger = LoggerFactory.getLogger(UserTokenHttpProxy.class);

    @Value("${user.center.remote.url}")
    private String userCenterUrl;

    private static final String USERCENTER_SUCCESS_CODE = "0000";

    private static final String USERCENTER_CONNECT_FAIL = "a100007";


    public UserTokenResponse findUserInfo(UserInfoQueryRequest userInfoQueryRequest,
                                          HttpHeaders httpHeaders) {
        return postRemoteUserCenter(userCenterUrl + "/findUserInfo", userInfoQueryRequest, httpHeaders);

    }


    private UserTokenResponse postRemoteUserCenter(String url, Object requestParam, HttpHeaders httpHeaders) {
        UserTokenResponse baseApiResponse = null;
        try {
            String returnVal = HttpPoolProxy.postJson(url, JsonUtil.objectToJson(requestParam), 2000, 1000, 2000,
                    httpHeaders.toSingleValueMap());
            if (StringUtils.isNotBlank(returnVal)) {
                baseApiResponse = JSON.parseObject(returnVal, UserTokenResponse.class);
                if (USERCENTER_CONNECT_FAIL.equals(baseApiResponse.getCode())) {
                    throw new AuthClientApiException(AuthServiceStatusEnum.USER_CENTER_CONNECT_FAIL.getCode(), AuthServiceStatusEnum.USER_CENTER_CONNECT_FAIL.getMessage());
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return baseApiResponse;
    }

}
