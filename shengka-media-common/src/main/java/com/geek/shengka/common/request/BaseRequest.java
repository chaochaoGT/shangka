package com.geek.shengka.common.request;

import com.geek.shengka.common.basemodel.PageRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求参数基类
 * */
@Data
@EqualsAndHashCode(callSuper=false)
public class BaseRequest extends PageRequest {

	/**
	 * 接口版本号
	 */
	private String version;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 请求流水号
	 */
	private String reqJrnNo;
	/**
	 * 请求时间戳
	 */
	private String reqDatetime;
	/**
	 * 请求方法名
	 */
	private String txCode;
	
	/**
	 * 达观设备id
	 */
	private String imei;
	
	/**
	 * 页面来源：home：首页，mini_video：小视频页，category_video：分类页

	 */
	private String sceneType;
	
	/**
	 * 达观推荐请求id
	 */
	private String recRequestId;
	
	/**
	 * 播放时长
	 */
	private String playTime;

}
