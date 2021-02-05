package com.geek.shengka.common.basemodel;

public enum ResponseCode implements ReturnBeanCode {

    /***********************************通用错误码*****************************************/
    OK(0, "请求成功"),
    SYSTEM_HEALTH(200, "success"),
    INVALID_FIELDS(100001, "请求参数非法"),
    SYSTEM_ERROR(100002, "系统错误或访问过于频繁"),
    SERVICE_NOT_AVALIABLE(100003, "服务不可用"),
    SIGNATURE_VERIFY_FAILED(100004, "签名检查失败"),
    BIZ_SYSTEM_ERROR(100005, "业务系统处理异常"),
    CHARACTER_NOT_SUPPORTED(100006, "字符集不被支持"),
    SERVICE_NOT_FOUND(100007, "服务未找到"),
    REQUEST_PATH_ERROR(100008, "访问路径出错"),
    REQUIRED_PARAMS_MISSING(100009, "缺少必须请求参数"),
    FREQUENTLY(100009, "拒绝频繁访问"),
    DATA_OPT_EXCEPTION_MISSING(100010, "数据操作异常"),
    NOT_SUPPORT_REQUEST_EXCEPTION(100011, "不支持的请求类型"),
    NOT_SAME_UID_BEUID(100012, "举报人不能举报自己"),
    NOT_REPART_REPORT_BEUID(100012, "不能重复举报"),
    BOX_GET_LIMIT(100014, "宝箱开启已达上限"),
    BOX_IS_NOT_EXIST(100013, "宝箱不存在");



    private int code;
    private String message;


    private ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public int getReturnCode() {
		return this.code;
	}

	@Override
	public String getReturnMessage() {
		return this.message;
	}

 

}
