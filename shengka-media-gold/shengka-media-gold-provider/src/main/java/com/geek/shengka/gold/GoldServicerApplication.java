package com.geek.shengka.gold;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableApolloConfig
@EnableHystrix
@EnableDiscoveryClient // 激活服务发现客户
@EnableFeignClients
@EnableTransactionManagement
@MapperScan({"com.geek.shengka.gold.mapper"})
@ComponentScan(basePackages = "com.geek.shengka")
public class GoldServicerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoldServicerApplication.class, args);
	}

}
