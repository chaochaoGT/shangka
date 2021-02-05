package com.geek.shengka.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author: cuihuayang
 * Date: 2018/12/18 11:15
 * Description:
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String postJson(String url,String jsonData){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(list);
        HttpEntity<String> formEntity = new HttpEntity<>(jsonData, headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        logger.info("HttpUtil POST 请求 URL :{} 响应的结果 :{}",url,result);
        return result;
    }

    public static  <R> R postJson(String url,String jsonData,Class<R> rClass){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(list);
        HttpEntity<String> formEntity = new HttpEntity<>(jsonData, headers);
        R result = restTemplate.postForObject(url, formEntity, rClass);
        logger.info("HttpUtil POST 请求 URL :{} 响应的结果 :{}",url,JsonUtil.objectToJson(result));
        return result;
    }

    public static String postFormData(String url,HashMap<String,Object> map ){
        MultiValueMap<String, Object> multiValueMap= new LinkedMultiValueMap<>();
        map.forEach(multiValueMap::add);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(list);
        HttpEntity<MultiValueMap> formEntity = new HttpEntity<>(multiValueMap, headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        logger.info("HttpUtil POST 请求 URL :{} 响应的结果 :{}",url,result);
        return result;
    }

    public static <R> R postFormData(String url,HashMap<String,Object> map,Class<R> rClass ){
        MultiValueMap<String, Object> multiValueMap= new LinkedMultiValueMap<>();
        map.forEach(multiValueMap::add);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(list);
        HttpEntity<MultiValueMap> formEntity = new HttpEntity<>(multiValueMap, headers);
        R result = restTemplate.postForObject(url, formEntity, rClass);
        logger.info("HttpUtil POST 请求 URL :{} 响应的结果 :{}",url,result);
        return result;
    }


    public static String getForString(String url){
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url,String.class);
        logger.info("HttpUtil POST 请求 URL :{} 响应的结果 :{}",url,result);
        return result;
    }

    public static <R> R getForEntity(String url,Class<R> rClass) {
        RestTemplate restTemplate = new RestTemplate();
        R result = restTemplate.getForObject(url,rClass);
        logger.info("HttpUtil POST 请求 URL :{} 响应的结果 :{}",url,result);
        return result;
    }

}
