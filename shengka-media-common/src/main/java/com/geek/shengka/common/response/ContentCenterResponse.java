package com.geek.shengka.common.response;

import org.springframework.stereotype.Component;
import lombok.Data;

/**
 * 内容中心请求
 * 
 * @author xuxuelei
 */
@Component
@Data
public class ContentCenterResponse {
	 private String token;
	 private String bizCode;
	 private Integer expireTime;
}
