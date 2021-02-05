package com.geek.shengka.content.controller;

import com.geek.shengka.common.basemodel.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geek.shengka.content.response.OssSecurityTokenInfo;
import com.geek.shengka.content.service.impl.OssTokenServiceImpl;

@RestController
@RequestMapping("/v1/oss/token")
public class OssTokenController {
	
	@Autowired
    private OssTokenServiceImpl ossTokenServiceImpl;

    @GetMapping("")
	public BaseResponse<OssSecurityTokenInfo> status() {
		OssSecurityTokenInfo ossSecurityTokenInfo = ossTokenServiceImpl.getOssToken();
		if(ossSecurityTokenInfo!=null) {
			return BaseResponse.newSuccess(ossSecurityTokenInfo);
		}
		return BaseResponse.newFailure();
	}
	
	
}
