package com.geek.shengka.content.config.oss;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "oss")
@Data
public class OssConfig {
	private String endpoint;
	private String accesskeyId;
	private String accesskeySecret;
	private String roleArn;
	private String backetName;
	private String folder;
	private String roleSessionName="external-username";
	private Long tokenDurationSeconds=3600L;
}
