package com.geek.shengka.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.annotation.OnlyUserIgnoreToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.UserTokenResponse;
import com.geek.shengka.common.constant.CommonConstants;
import com.geek.shengka.common.context.BaseContextHandler;
import com.geek.shengka.common.enums.AuthServiceStatusEnum;
import com.geek.shengka.common.exception.auth.AuthClientApiException;
import com.geek.shengka.common.exception.auth.ClientForbiddenException;
import com.geek.shengka.common.proxy.UserTokenHttpProxy;
import com.geek.shengka.common.request.UserInfoQueryRequest;
import com.geek.shengka.common.response.UserInfoResponse;
import com.geek.shengka.common.util.RSACoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * by gcl
 */
@SuppressWarnings("ALL")
@Slf4j
public class ClientAuthRestInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ClientAuthRestInterceptor.class);

    @Autowired
    private UserTokenHttpProxy userTokenHttpProxy;

    @Value("${user.center.header.prikey}")
    private String prikey;
    @Value("${user.center.header.platform-id}")
    private String dbPlatformId;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler == null || !(handler instanceof HandlerMethod)) {
            logger.info(request.getRequestURI() + "====>>>handler is null or type not support! !,请检查自己的ip 端口号，请求方式,地址单词有没有错误！！！！");
            throw new ClientForbiddenException("handler is null or type not support! !,请检查自己的ip 端口号，请求方式,地址单词有没有错误！！！！");
        }

        try {
            String appId = request.getHeader(CommonConstants.USERCENTER_KEY_APPID);
            String token = request.getHeader(CommonConstants.USERCENTER_KEY_TOKEN);
            String sign = request.getHeader(CommonConstants.USERCENTER_KEY_SIGN);
            String encrptPlatformId = request.getHeader(CommonConstants.USERCENTER_KEY_PLATFORMID);
            String source = request.getHeader(CommonConstants.USERCENTER_KEY_SOURCE);
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 配置该注解，说明不进行服务拦截
            IgnoreClientToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreClientToken.class);
            if (annotation == null) {
                annotation = handlerMethod.getMethodAnnotation(IgnoreClientToken.class);
            }
            if (annotation != null) {
                return super.preHandle(request, response, handler);
            }

            if (handlerMethod.getMethodAnnotation(OnlyUserIgnoreToken.class) != null) {
                if (StringUtils.isNotBlank(appId)
                        && StringUtils.isNotBlank(token)
                        && StringUtils.isNotBlank(sign)
                        && StringUtils.isNotBlank(encrptPlatformId)
                        && StringUtils.isNotBlank(source)) {
                    chackTokenSaveLocal(request, appId, token, sign, encrptPlatformId, source);
                }
                return super.preHandle(request, response, handler);
            }

            if (StringUtils.isEmpty(encrptPlatformId)) {
                throw new AuthClientApiException(AuthServiceStatusEnum.AUTH_MISS_PARAMS_EXCEPTION.getCode(), request.getRequestURI() + "====>>>" + AuthServiceStatusEnum.AUTH_MISS_PARAMS_EXCEPTION.getMessage());
            }

            if (StringUtils.isEmpty(source)) {
                throw new AuthClientApiException(AuthServiceStatusEnum.AUTH_MISS_PARAMS_EXCEPTION.getCode(), request.getRequestURI() + "====>>>" + AuthServiceStatusEnum.AUTH_MISS_PARAMS_EXCEPTION.getMessage());
            }

            String platformId = RSACoder.decryptByPrivateKey(encrptPlatformId, prikey);
            if (!dbPlatformId.equals(encrptPlatformId)) {
                logger.error(request.getRequestURI() + "====>>>platformId解密校验失败,encrptPlatformId:{},platformId:{}", encrptPlatformId, platformId);
                throw new AuthClientApiException(AuthServiceStatusEnum.AUTH_FAIL.getCode(), AuthServiceStatusEnum.AUTH_FAIL.getMessage());
            }

            if (StringUtils.isEmpty(appId) ||
                    StringUtils.isEmpty(token) ||
                    StringUtils.isEmpty(sign)) {
                throw new AuthClientApiException(AuthServiceStatusEnum.AUTH_APPID_MISS_EXCEPTION.getCode(), AuthServiceStatusEnum.AUTH_APPID_MISS_EXCEPTION.getMessage());
            }
            chackTokenSaveLocal(request, appId, token, sign, encrptPlatformId, source);
            return super.preHandle(request, response, handler);

        } catch (Exception e) {
            logger.error(request.getRequestURI() + "====>>>" + e.getMessage(), e);
            setNoAuthorizationRespData(response);
            return false;
        }

    }


    /***
     *
     * 验证用户token 保存theadLocal
     * @param request
     * @param appId
     * @param token
     * @param sign
     * @param encrptPlatformId
     * @param source
     */
    public final void chackTokenSaveLocal(HttpServletRequest request, String appId, String token, String sign, String encrptPlatformId, String source) {
        UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
        userInfoQueryRequest.setAppId(appId);
        UserTokenResponse userInfo = userTokenHttpProxy.findUserInfo(userInfoQueryRequest, getHttpHeaders(appId, token, sign, encrptPlatformId, source));
        if (userInfo == null) {
            throw new AuthClientApiException(AuthServiceStatusEnum.USER_CENTER_CONNECT_FAIL.getCode(), AuthServiceStatusEnum.USER_CENTER_CONNECT_FAIL.getMessage());
        }
        UserInfoResponse userInfoData = userInfo.getData();
        if (userInfoData == null) {
            throw new AuthClientApiException(AuthServiceStatusEnum.AUTH_GET_USER_ERROR_EXCEPTION.getCode(), AuthServiceStatusEnum.AUTH_GET_USER_ERROR_EXCEPTION.getMessage());
        }
        request.setAttribute(CommonConstants.CONTEXT_KEY_UID,userInfoData.getId());
        BaseContextHandler.seUid(userInfoData.getId());
        BaseContextHandler.setNickname(userInfoData.getNickname());
        BaseContextHandler.setPlatformId(encrptPlatformId);
        BaseContextHandler.setAppId(appId);
        BaseContextHandler.setPhoneNum(userInfoData.getPhoneNum());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.debug("request url====>>>" + request.getRequestURI());
        BaseContextHandler.remove();
    }

    private HttpHeaders getHttpHeaders(String appId, String token, String sign, String platformId, String source) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("app-id", appId);
        httpHeaders.add("token", token);
        httpHeaders.add("sign", sign);
        httpHeaders.add("platform-id", platformId);
        httpHeaders.add("source", source);
        return httpHeaders;
    }


    /**
     * 设置认证不通过
     *
     * @param response
     */
    public void setNoAuthorizationRespData(HttpServletResponse response) throws IOException {
        String message = "token 验证失败";
        BaseResponse resultData = BaseResponse.newFailure(AuthServiceStatusEnum.AUTH_FAIL.getCode(), AuthServiceStatusEnum.AUTH_FAIL.getMessage());
        String resultStr = JSON.toJSONString(resultData);
        response.setContentType("application/json;charset=utf-8");
        response.getOutputStream().write(resultStr.getBytes());
    }

}
