package com.geek.shengka.common.basemodel;

import java.io.Serializable;

/**
 * API返回接口数据格式
 */
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 接口公共返回参数标准
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseResponse<T> implements Serializable{

	private static final long serialVersionUID = -4505655308965878999L;

	// 返回数据
	// 这里的数据， 可以是单个对象
	// 可以是map , collection
	// 也可以是分页对象 PageData
	// 也可以是基本数据类型
	protected T data;

	// 返回码
	private int code;

	// 返回描述
	private String msg;
	
	private long timestamp;

	/**
	 * 推荐使用封装的静态方法newXXX方法进行构造对象
	 */
	public BaseResponse() {
	}

	/**
	 * 根据基本返回码来构造返回对象,仅供暴露外部的静态方法调用
	 */
	public BaseResponse(ReturnBeanCode responseCode) {
		this.code = responseCode.getReturnCode();
		this.msg = responseCode.getReturnMessage();
		this.timestamp=System.currentTimeMillis();
	}

	public BaseResponse(int code ,String message) {
		this.code = code;
		this.msg = message;
		this.timestamp=System.currentTimeMillis();
	}
	
 
	@SuppressWarnings("rawtypes")
	public static BaseResponse newSuccess() {
		BaseResponse baseApiResponse = new BaseResponse(ResponseBeanCode.OK);
		return baseApiResponse;
	}

	/**
	 * 创建一个成功返回（可以指定返回数据）
	 */
	public static <T> BaseResponse<T> newSuccess(T data) {
		BaseResponse<T> baseApiResponse = new BaseResponse<T>(ResponseBeanCode.OK);
		baseApiResponse.setTimestamp(System.currentTimeMillis());
		baseApiResponse.setData(data);
		return baseApiResponse;
	}
	

	/**
	 * 创建一个成功返回（可以指定返回数据）
	 */
	public static <T> BaseResponse<T> health() {
		BaseResponse<T> baseApiResponse = new BaseResponse<T>(ResponseBeanCode.SYSTEM_HEALTH);
		return baseApiResponse;
	}

	
	@SuppressWarnings("rawtypes")
	public static BaseResponse newFailure() {
		BaseResponse baseApiResponse = new BaseResponse(ResponseBeanCode.BIZ_SYSTEM_ERROR);
		return baseApiResponse;
	}
	/**
	 * 创建一个失败返回（不带返回数据）
	 */
	public static <T> BaseResponse<T> newFailure(ReturnBeanCode responseCode) {
		return new BaseResponse<T>(responseCode);
	}
	/**
	 * 创建一个失败返回（带返回数据）
	 */
	public static <T> BaseResponse<T> newFailure(ReturnBeanCode responseCode, T data) {
		BaseResponse<T> baseApiResponse = newFailure(responseCode);
		baseApiResponse.setData(data);
		return baseApiResponse;
	}

	/**
	 * 创建一个失败返回
	 */
	public static BaseResponse newFailure(int code, String message) {
		BaseResponse baseApiResponse = new BaseResponse(code,message);
		return baseApiResponse;
	}


}
