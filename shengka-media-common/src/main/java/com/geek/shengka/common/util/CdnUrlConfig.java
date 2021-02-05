package com.geek.shengka.common.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * cdn地址集合
 * @author xuxuelei
 *
 */
@Component
@ConfigurationProperties(prefix = "cdn")
@Data
public class CdnUrlConfig {
    /**正式环境地址*/
	public static String domainUrl="http://mp4.ywan3.com";

	private String domain;
	@PostConstruct
	public void init() {
        domainUrl = domain;
	}
 
    

}


