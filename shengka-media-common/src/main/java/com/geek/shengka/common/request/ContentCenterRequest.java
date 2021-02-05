package com.geek.shengka.common.request;

import org.springframework.stereotype.Component;
import lombok.Data;

/**
 * 内容中心请求
 * 
 * @author xuxuelei
 */
@Component
@Data
public class ContentCenterRequest {
	 private String channelCode;
	 private String loginName;
	 private String loginPassword;
}
