package com.geek.shengka.content.monitor;

import javax.servlet.http.HttpServletRequest;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import org.springframework.web.bind.annotation.*;


/**
 *  服务状态监测接口
 * 
 * @author luoyong
 * @date 2019-08-06
 */
@RestController
@IgnoreClientToken
public class ServerStateController  {
   
	@GetMapping(value = "/monitor")
	public BaseResponse<String> status(HttpServletRequest request) {
		return BaseResponse.health();
	}
	
	 
	
}