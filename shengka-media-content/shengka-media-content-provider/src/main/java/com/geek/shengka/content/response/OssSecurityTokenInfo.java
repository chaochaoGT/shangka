package com.geek.shengka.content.response;

import lombok.Data;

@Data
public class OssSecurityTokenInfo {
	private String token;
	private String expiration;
	private String accessKeyId;
	private String accessKeySecret;
}
