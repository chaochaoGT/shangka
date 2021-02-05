package com.geek.shengka.common.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.common.http.HttpPoolProxy;
import com.geek.shengka.common.request.ContentCenterRequest;
import com.geek.shengka.common.response.ContentCenterResponse;
import com.geek.shengka.common.util.JwtHelper;
import io.jsonwebtoken.Claims;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 内容中心代理
 * 
 * @author xuxuelei
 */
@Component
@Data
public class ContentCenterProxy {
	private static final Logger logger = LoggerFactory.getLogger(ContentCenterProxy.class);


	private static final String CONTENT_KEY = "CONTENT:CENTER:SHENGKA:AUTH%s";
	
	private static final long expireTime = 60 * 60 * 1000; // 默认失效时间：1小时

    @Autowired
    private ContentUrlConfig contentUrlConfig;


	/**
	    *     获取权限中心token和业务线
	 *
	 * @param advanceRequest
	 * @return
	 */
	public ContentCenterResponse getAuthToken() {
		try {
             
			// 1、先从redis缓存取数据
			ContentCenterResponse authToken = CacheProvider.getObject(CONTENT_KEY, ContentCenterResponse.class);
            if (authToken != null && checkExpireToken(authToken.getExpireTime()) ) {
				return authToken;
			} else {
				ContentCenterRequest contentRequest = new ContentCenterRequest();
				contentRequest.setChannelCode(contentUrlConfig.getChannelCode());
				contentRequest.setLoginName(contentUrlConfig.getLoginName());
				contentRequest.setLoginPassword(contentUrlConfig.getLoginPassword());
				// 2、缓存不存在， 直接从远程取
				String responseData = HttpPoolProxy.postJson(contentUrlConfig.getAuthUserCenterUrl(), JSON.toJSONString(contentRequest), 700, 700, 1000);
				if (StringUtils.isNotBlank(responseData)) {
					try {
						BaseResponse<String> baseResponse = JSON.parseObject(responseData, new TypeReference<BaseResponse<String>>(){});
						if (baseResponse.getData() != null) {
							String token =  baseResponse.getData();
							ContentCenterResponse advanceResponse = new  ContentCenterResponse();
							advanceResponse.setBizCode(contentUrlConfig.getChannelCode());
							advanceResponse.setToken(token);
							advanceResponse.setExpireTime(getExpireToken(token,contentUrlConfig.getLoginSecret()));
							// 3、设置回redis缓存，失效期为1小时
							CacheProvider.setObject(CONTENT_KEY, advanceResponse, expireTime);
							return advanceResponse;
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
        logger.error("认证失败，返回为null");
		return null;
	}

    /**
     *
     * @param expireTime
     * @return
     */
    private boolean checkExpireToken(Integer expireTime) {
        Long currentTime = System.currentTimeMillis()/1000;
        return expireTime.compareTo(currentTime.intValue())>0;
    }
    /**
     * 获取失效时间戳
     * @param token
     * @param loginSecret
     * @return
     */
    private Integer getExpireToken(String token, String loginSecret) {
        try {
            Claims claims = JwtHelper.parseJWT(token, loginSecret);
            return (Integer)claims.get("exp");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return 0;
    }



}
