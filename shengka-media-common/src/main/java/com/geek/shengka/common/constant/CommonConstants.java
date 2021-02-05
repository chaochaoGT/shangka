package com.geek.shengka.common.constant;

public class CommonConstants {

    public final static String RESOURCE_TYPE_MENU = "menu";
    public final static String RESOURCE_TYPE_BTN = "button";
    public static final Integer EX_TOKEN_ERROR_CODE = 40101;
    // 用户token异常
    public static final Integer EX_USER_INVALID_CODE = 40102;
    public static final Integer EX_USER_PASS_INVALID_CODE = 400103;
    public static final Integer EX_IMAGECODE_CODE = 400112;
    // 客户端token异常
    public static final Integer EX_CLIENT_INVALID_CODE = 40131;
    public static final Integer EX_CLIENT_FORBIDDEN_CODE = 40331;
    public static final Integer EX_OTHER_CODE = 500;
    public static final String CONTEXT_KEY_PLATFORMID = "currentPlatformId";
    public static final String CONTEXT_KEY_X_ID = "currentXId";
    public static final String CONTEXT_KEY_NAME = "currentName";
    public static final String CONTEXT_KEY_NICKNAME = "currentNickname";
    public static final String CONTEXT_KEY_TOKEN = "currentToken";
    public static final String JWT_KEY_X_ID = "XId";
    //微服务客户端名称或是登陆用户名称
    public static final String JWT_KEY_X_NAME = "XName";
    //登陆用户昵称
    public static final String JWT_KEY_NICKNAME = "nickname";
    public static final String JWT_KEY_CODE = "code";
    //平台编号
    public static final String JWT_KEY_PLATFORMID = "platformId";
    //签名盐值
    public static final String JWT_KEY_SALTKEY = "saltKey";
    //新增外部服务应用id
    public static final String JWT_KEY_APPID = "appid";

    
    public static final String APP_CONFIG_KEY = "APP_CONFIG_KEY";
    
    public static final String DEFAULT_PAGE_SIZE = "default_page_size";


    //来源 安卓微信
    public static final String CONTEXT_KEY_SOURCE = "source";
    //appId
    public static final String CONTEXT_KEY_APPID = "app-id";
    //签名
    public static final String CONTEXT_KEY_SIGN = "sign";


    public static final String CONTEXT_KEY_PHONENUM = "phoneNum";
    public static final String CONTEXT_KEY_UID = "uid";



    //appId
    public static final String USERCENTER_KEY_APPID = "app-id";
    //签名
    public static final String USERCENTER_KEY_SIGN = "sign";
    //token
    public static final String USERCENTER_KEY_TOKEN = "token";
    //来源
    public static final String USERCENTER_KEY_SOURCE = "source";
    //平台
    public static final String USERCENTER_KEY_PLATFORMID = "platform-id";


}
