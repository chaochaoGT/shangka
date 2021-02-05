package com.geek.shengka.backend.config;

import com.geek.shengka.backend.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qubianzhong
 * @Date: 2019年6月24日
 */
@Configuration
public class Swagger2Config {

    @Value("${swagger2.show:true}")
    private Boolean swaggerShow;

    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     *
     * @param
     * @return springfox.documentation.spring.web.plugins.Docket
     * @author qubianzhong
     * @Date 2019年6月24日
     */
    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(CommonConstant.CONTEXT_KEY_TOKEN).description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.geek.shengka.backend.controller"));
        if (this.swaggerShow) {
            apiSelectorBuilder.paths(PathSelectors.any());
        } else {
            apiSelectorBuilder.paths(PathSelectors.none());
        }
        return apiSelectorBuilder.build().globalOperationParameters(pars);
    }

    /**
     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("声咖后台")
                //创建人
                .contact(new Contact("行者常至", "", ""))
                //版本号
                .version("1.0")
                //描述
                .description("如果工作是一种乐趣，那么人间就是天堂！")
                .build();
    }

}
