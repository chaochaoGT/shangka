package com.geek.shengka.eureka.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import com.geek.shengka.eureka.model.BaseApiResponse;

/**
 *  服务状态监测接口
 * 
 * @author luoyong
 * @date 2019-08-06
 */
@RestController
@RequestMapping("skmediaeureka")
public  class EurekaServerStateController  {

   
	@GetMapping(value = "/monitor")
	public BaseApiResponse<String> status(HttpServletRequest request) {
		return BaseApiResponse.health();
	}
	
	 
	
}