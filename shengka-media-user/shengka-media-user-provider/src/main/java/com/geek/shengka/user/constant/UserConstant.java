package com.geek.shengka.user.constant;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-14 8:36
 */
public class UserConstant {


    public static long REDIS_EXIST_TIME= 60*1000L;
    //频道模块==================
    public static final String DEFAULT_CATEGORYS_CHANNEL = "shengka:defCategory:channle:%s";
    /**系统默认*/
    public static final Integer CATEGORYS_TYPE_1 = 1;
    /**可选*/
    public static final Integer CATEGORYS_TYPE_2 = 2;
    /**频道走视频推荐*/
    public static final String CATEGORYS_BIZ_CODE = "laowangshipin";

    //话题模块================
    /**0 订阅  未关注*/
    public static int subscrip_status_0=0;
    /**1 取消订阅  已关注*/
    public static int subscrip_status_1=1;

    //视频发布  0-审核中，1-审核通过， 2-审核失败
    public static int push_video_enble_1=1;

    public static final String WATCH_MODE_0 = "0";
    public static Integer default_pageIndex=0;
}
