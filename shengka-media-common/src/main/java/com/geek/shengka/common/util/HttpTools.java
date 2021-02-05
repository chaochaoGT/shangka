package com.geek.shengka.common.util;

import org.apache.http.client.methods.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import java.net.URI;

/**
 * http远程操作
 *
 * @author xuxuelei
 */
public class HttpTools {

    private static final Logger logger = LoggerFactory.getLogger(HttpTools.class);

    public static String getForString(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        logger.info("HttpUtil POST 请求 URL :{} 响应的结果 :{}", url, result);
        return result;
    }

    public static <R> R getForEntity(String url, Class<R> rClass) {
        RestTemplate restTemplate = new RestTemplate();
        R result = restTemplate.getForObject(url, rClass);
        logger.info("HttpUtil POST 请求 URL :{} 响应的结果 :{}", url, result);
        return result;
    }

    public static <R> R getJson(String url, String jsonData, Class<R> rClass) {
        RestTemplate restTemplate = new RestTemplate();
        R result = restTemplate.getForObject(url, rClass,jsonData);
        logger.info("HttpUtil POST 请求 URL :{} 响应的结果 :{}", url, result);
        return result;
    }


    public static <R> R getForJson(String url,  Object o, Class<R> rClass) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientRestfulHttpRequestFactory());
        HttpEntity<Object> entity = new HttpEntity<Object>(o, headers);
        ResponseEntity<R> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, rClass);
        logger.info("HttpUtil POST 请求 URL :{} 响应的结果 :{}", url, responseEntity);
        if(responseEntity.getStatusCodeValue()==200){
            return responseEntity.getBody();
        }
        return responseEntity.getBody();
    }

    /**
     * RestTemplate 原生api不支持body  进行扩展
     */
    private static final class HttpComponentsClientRestfulHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {
        @Override
        protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
            if (httpMethod == HttpMethod.GET) {
                return new HttpGetRequestWithEntity(uri);
            }
            return super.createHttpUriRequest(httpMethod, uri);
        }
    }

    private static final class HttpGetRequestWithEntity extends HttpEntityEnclosingRequestBase {
        public HttpGetRequestWithEntity(final URI uri) {
            super.setURI(uri);
        }

        @Override
        public String getMethod() {
            return HttpMethod.GET.name();
        }
    }


}
