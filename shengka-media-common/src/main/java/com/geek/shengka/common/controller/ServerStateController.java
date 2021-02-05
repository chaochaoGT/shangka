package com.geek.shengka.common.controller;

import javax.servlet.http.HttpServletRequest;

import com.geek.shengka.common.basemodel.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *  服务状态监测接口
 * 
 * @author jiangxinqiang
 * @date 2019-3-14
 */
public abstract class ServerStateController  {

   
	@RequestMapping(method = RequestMethod.GET, value = "/monitor")
	@ResponseBody
	public BaseResponse<String> status(HttpServletRequest request) {
		return BaseResponse.health();
	}
	
	 
	
}