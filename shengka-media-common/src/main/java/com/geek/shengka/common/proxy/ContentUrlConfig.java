package com.geek.shengka.common.proxy;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Filename: ContentUrlConfig
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/1 ;
 */
@Component
@ConfigurationProperties(prefix = "content")
@Data
public class ContentUrlConfig {
    /** 权限中心地址**/
    private String authUserCenterUrl;
    /** 渠道**/
    private String channelCode;
    /** 登录名**/
    private String loginName;
    /** 登录密码**/
    private String loginPassword;
    /**token密钥*/
    private String loginSecret;
    /** 内容中心地址**/
    private String contentCenterUrl;

    /** 内容中心-多条件获取视频接口**/
    private String multiConditionMedias;

    /**行为上报*/
    private String behaviorReportedMethod;
 
    /** 根据视频id删除视频**/
    private String contentDeleteVideosMethod;
    /** 根据视频id获取语音列表**/
    private String contentVoicesMethod;
    /** 根据语音id删除语音**/
    private String contentDeleteVoicesMethod;
 
    
}
