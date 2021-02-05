package com.geek.shengka.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//@EnableApolloConfig
@EnableHystrix
@EnableDiscoveryClient // 激活服务发现客户
@EnableFeignClients
@EnableTransactionManagement
@MapperScan({"com.geek.shengka.user.mapper"})
@ComponentScan(basePackages = "com.geek.shengka")
public class UserServicerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServicerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
