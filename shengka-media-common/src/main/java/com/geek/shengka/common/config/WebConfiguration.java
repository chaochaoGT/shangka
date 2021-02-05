package com.geek.shengka.common.config;

import com.geek.shengka.common.interceptor.ClientAuthRestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统
 *
 * @author: yunfei
 * @create: 2019-08-07 22:41
 **/
@Configuration
@Primary
public class WebConfiguration implements WebMvcConfigurer {


    /**
     *  添加客户端鉴权拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getClientAuthRestInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(getExcludePath());
    }

    @Bean
    ClientAuthRestInterceptor getClientAuthRestInterceptor(){
        return new ClientAuthRestInterceptor();
    }
    /***
     * 过滤列表
     * @return
     */
    private final List<String> getExcludePath() {
        List<String> list = new ArrayList<>();
        return list;
    }
}
