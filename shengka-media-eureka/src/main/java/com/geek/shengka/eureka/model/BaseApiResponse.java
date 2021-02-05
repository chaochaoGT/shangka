package com.geek.shengka.eureka.model;

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
public class BaseApiResponse<T> implements Serializable{

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
	private String message;

	/**
	 * 推荐使用封装的静态方法newXXX方法进行构造对象
	 */
	public BaseApiResponse() {
	}

	/**
	 * 根据基本返回码来构造返回对象,仅供暴露外部的静态方法调用
	 */
	public BaseApiResponse(ReturnCode responseCode) {
		this.code = responseCode.getReturnCode();
		this.message = responseCode.getReturnMessage();
	}

	/**
	 * 创建一个成功返回（可以指定返回数据）
	 */
	public static <T> BaseApiResponse<T> health() {
		BaseApiResponse<T> baseApiResponse = new BaseApiResponse<T>(ResponseCode.SYSTEM_HEALTH);
		return baseApiResponse;
	}


}
