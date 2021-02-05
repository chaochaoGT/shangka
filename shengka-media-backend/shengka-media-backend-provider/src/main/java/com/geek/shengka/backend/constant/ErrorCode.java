package com.geek.shengka.backend.constant;


import com.geek.shengka.backend.util.DataResult;

/**
 * @Author luoyong
 * @Description 业务处理返回的错误码
 * @Date 11:18 2019/3/28
 **/
public enum ErrorCode {

    SYS_ERROR(1000, "系统繁忙，请稍后重试"),
    PARAM_REQUIRED(10001, "参数为必填"),
    PARAM_ERROR(10002, "参数错误"),
    UPDATE_ERROR(10003, "更新失败"),
    FILE_IS_NULL(10004, "上传文件为空"),
    FILE_PARSE_FAIL(10005, "文件解析失败"),
    MAP_TO_BEAN_FAIL(10006, "请检查数据，类型转换失败"),
    SYSTEM_INVALID_FAIL(10007, "系统类型校验失败(1-安卓，2-IOS,3-H5)"),
    DATE_INVALID_FAIL(10008, "时间格式校验失败"),
    DECIMAL_INVALID_FAIL(10009, "金额需为两位小数"),
    INT_INVALID_FAIL(10009, "参数需为正整数"),
    PROFIT_LIST_IS_NULL(10010, "参数需为正整数"),
    ;

    private String msg;

    private Integer code;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public static DataResult toResult(ErrorCode errorCode) {
        return DataResult.fail(errorCode.getCode().intValue(), errorCode.getMsg());
    }
}
