package com.geek.shengka.backend.permission;


import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.backend.config.WebMvcConfig;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UserTokenHttpProxy {

    @Value("${permission.center.remote.url}")
    private String permissionCenterUrl;

    @Lazy
    @Autowired(required = false)
    private RestTemplate restTemplate;


    public BaseApiResponse<Boolean> verifyToken(VerifyTokenRequest verifyTokenRequest, HttpHeaders httpHeaders) {
        String json = JSONObject.toJSONString(verifyTokenRequest);
        BaseApiResponse  baseApiResponse = postJsonWithHeaders(permissionCenterUrl + "/auth/getAuth", json, BaseApiResponse.class, httpHeaders);
    	//log.info("verifyToken request,{},{},{}",permissionCenterUrl + "/auth/getAuth",json,httpHeaders);
    	//log.info("verifyToken response,{}",JSONObject.toJSONString(baseApiResponse));
    	
        return baseApiResponse;
    }


    public BaseApiResponse<List> getUserInfo(HttpHeaders httpHeaders) {

    	 BaseApiResponse  baseApiResponse  =postJsonWithHeaders(permissionCenterUrl + "/getUser/userInfo", null
                , BaseApiResponse.class, httpHeaders);
     	//log.info("getUserInfo request,{},{},{}",permissionCenterUrl + "/getUser/userInfo",httpHeaders,baseApiResponse);
    	//log.info("verifyToken response,{}",JSONObject.toJSONString(baseApiResponse));
     	return baseApiResponse;
    }

    public <R> R postJsonWithHeaders(String url, String jsonData, Class<R> rClass,
                                     HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(list);
        HttpEntity<String> formEntity = new HttpEntity<>(jsonData, headers);
        return restTemplate.postForObject(url, formEntity, rClass);
    }

}
