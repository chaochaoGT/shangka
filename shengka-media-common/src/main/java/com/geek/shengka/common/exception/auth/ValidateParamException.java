package com.geek.shengka.common.exception.auth;

import com.geek.shengka.common.exception.BaseException;
/**
 * 验证参数是否准确
 * 
 */
public class ValidateParamException extends BaseException {
	private String code;
	private String msg;
	private boolean success;

	
	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public ValidateParamException(String code, String msg, boolean success) {
		this.code = code;
		this.msg = msg;
		this.success = success;
	}

	

}
