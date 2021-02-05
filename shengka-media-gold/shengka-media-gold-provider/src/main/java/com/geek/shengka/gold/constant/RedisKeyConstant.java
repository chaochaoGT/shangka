package com.geek.shengka.gold.constant;

/**
 * @description: redis key工具类
 * @author: yunfei
 * @create: 2019:06:04 16:52
 **/
public class RedisKeyConstant {

    private RedisKeyConstant(){}

    /**用户账户剩余金币*/
    public static final String USER_ACCOUNT_BALANCE="sk:user:balance:";
    /**每天用户活跃度*/
    public static final  String DAY_USER_ACTIVE="sk:user:active:";
    /**每天用户活跃度*/
    public static final  String DAY_USER_ACTIVE_MAX="sk:user:active:max";
    /**每天用户获得金币数*/
    public static final  String DAY_USER_COIN="sk:user:coin:";
    /**每天用户金币上限*/
    public static final  String DAY_USER_LIMIT="sk:user:limit";
    
    /**看视频金币配置1.2.0*/
    public static final  String MEIDA_COIN_CONFIG="sk:media:coinConfig:1.2.0";
    
    /**看视频金币配置1.3.0*/
    public static final  String MEIDA_COIN_CONFIG_1_3_0="sk:media:coinConfig:1.3.0";
    
    /**过期时间*/
    public static final long MEIDA_COIN_CONFIG_EXPIRED_TIME = 2L;
    
    /**夺宝获奖人 滚动条集合，保留10位*/
    public static final  String USER_TREASURE_WIN="sk:user:treasureWin";
    
    /**夺宝消费记录key*/
    public static final  String USER_TREASURE_RECORD="sk:user:treasure:record:";

    /**用户视频获取金币key 用于10分钟内不可重复判断*/
    public static final String USER_VIEW_GETCOIN="sk:user:view:getCoin:";

    /**账户信息缓存*/
    public static final String USER_ACCOUNT_INFO="sk:user:account:";
    /**账户流水缓存*/
    public static final String USER_FLOW_INFO="sk:user:accountFlow:";

    /**体现操作记录key*/
    public static final  String USER_WITHDRAW_RECORD="sk:user:withdraw:record:";
}
