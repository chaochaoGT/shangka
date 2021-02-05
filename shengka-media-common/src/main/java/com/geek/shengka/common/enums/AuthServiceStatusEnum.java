package com.geek.shengka.common.enums;


import com.geek.shengka.common.basemodel.ReturnBeanCode;
import lombok.Getter;

/**
 * Author: cuihuayang
 * Date: 2019/3/6 20:16
 * Description:
 */
@Getter
public enum AuthServiceStatusEnum implements ReturnBeanCode {
    /**
     * 认证
     */
    AUTH_MISS_PARAMS_EXCEPTION(100000, "header中platform-id或source为空"),
    AUTH_TOKEN_ERROR_EXCEPTION(100000, "userCode,redisToken 为null 或者 redisToken 和 请求头中的token不一样, 返回token验证失败"),
    AUTH_NOT_FOUND_SCRET_ERROR_EXCEPTION(100002, "根据userCode从数据库中获取不到对应的秘钥对象"),
    AUTH_DECRPTE_SCRET_ERROR_EXCEPTION(100003, "经过公钥解密的字符串不对"),
    AUTH_USERCODE_ERROR_EXCEPTION(100004, "解密后的loginType与客户端不一致,或者解密后的userCode与redis中存储的userCode不一致"),
    AUTH_SUCESS(100005, "验证成功"),
    AUTH_APPID_MISS_EXCEPTION(100005, "appId,token,sign参数为空"),
    AUTH_GET_USER_ERROR_EXCEPTION(100006, "获取用户信息失败"),
    AUTH_FAIL(100007, "token验证失败"),
    USER_CENTER_CONNECT_FAIL(100007, "user center token会话失败"),
    AUTH_MISS_PARMS(100008, "缺少必要参数");


    private int code;
    private String message;

    AuthServiceStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public int getReturnCode() {
        return code;
    }

    @Override
    public String getReturnMessage() {
        return message;
    }
}
