package com.geek.shengka.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geek.shengka.backend.constant.CommonConstant;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.permission.BaseApiResponse;
import com.geek.shengka.backend.permission.UserTokenHttpProxy;
import com.geek.shengka.backend.permission.VerifyTokenRequest;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.geek.shengka.backend.permission.BaseApiResponse.SUCCESS_CODE;


/**
 * @ClassName WebMvcConfig
 * @Description 验权拦截配置
 * @Author luoyong
 * @Date 2019/3/30 11:56
 * @Version 1.0
 */
@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private UserTokenHttpProxy userTokenHttpProxy;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 用户权限拦截器
        registry.addInterceptor(new UserAuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private class UserAuthInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String url = request.getRequestURI();
            String token = request.getHeader(CommonConstant.CONTEXT_KEY_TOKEN);
            if (StringUtils.isBlank(token)) {
                log.error(request.getRequestURL() + "\t token不能为空");
                throw new BaseException("token不能为空");
            }

            VerifyTokenRequest tokenRequest = new VerifyTokenRequest();
            tokenRequest.setUrl(url);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(CommonConstant.CONTEXT_KEY_TOKEN, token);
            //验权
            BaseApiResponse<Boolean> baseApiResponse = userTokenHttpProxy.verifyToken(tokenRequest, httpHeaders);
            if (SUCCESS_CODE.equals(baseApiResponse.getCode())) {
                //验权通过
                //获取登录用户信息
                BaseApiResponse<List> userInfoResponse = userTokenHttpProxy.getUserInfo(httpHeaders);
                if (SUCCESS_CODE.equals(userInfoResponse.getCode())) {
                    Map userInfoResponse1 = (Map) userInfoResponse.getData().get(0);
                    request.setAttribute("loginName", userInfoResponse1.get("loginName"));
                    return true;
                }
            }
            throw new BaseException("验权失败！！！");
        }
    }


    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        //消息转换器，一般情况下可以省略，只需要添加相关依赖即可
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
                jsonConverter.setObjectMapper(new ObjectMapper());
                jsonConverter.setSupportedMediaTypes(ImmutableList.of(new MediaType("application", "json",
                        MappingJackson2HttpMessageConverter.DEFAULT_CHARSET), new MediaType("text", "html",
                        MappingJackson2HttpMessageConverter.DEFAULT_CHARSET)));
            }
        }
        return restTemplate;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        //Httpclient连接池，长连接保持30秒
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);

        //设置总连接数
        connectionManager.setMaxTotal(1000);
        //设置同路由的并发数
        connectionManager.setDefaultMaxPerRoute(1000);

        //设置header
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.04"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3"));
        headers.add(new BasicHeader("Connection", "keep-alive"));
        //创建HttpClient
        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setDefaultHeaders(headers)
                //设置重试次数
                .setRetryHandler(new DefaultHttpRequestRetryHandler(3, true))
                //设置保持长连接
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .build();

        //创建HttpComponentsClientHttpRequestFactory实例
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        //设置客户端和服务端建立连接的超时时间
        requestFactory.setConnectTimeout(5000);
        //设置客户端从服务端读取数据的超时时间
        requestFactory.setReadTimeout(5000);
        //设置从连接池获取连接的超时时间，不宜过长
        requestFactory.setConnectionRequestTimeout(200);
        //缓冲请求数据，默认为true。通过POST或者PUT大量发送数据时，建议将此更改为false，以免耗尽内存
        requestFactory.setBufferRequestBody(false);
        return requestFactory;
    }


}
