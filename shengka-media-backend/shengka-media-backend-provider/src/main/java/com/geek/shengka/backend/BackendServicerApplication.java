package com.geek.shengka.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(scanBasePackages = "com.geek.shengka.backend")
@EnableTransactionManagement
@MapperScan({"com.geek.shengka.backend.mapper"})
@EnableSwagger2
public class BackendServicerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendServicerApplication.class, args);
    }

}
