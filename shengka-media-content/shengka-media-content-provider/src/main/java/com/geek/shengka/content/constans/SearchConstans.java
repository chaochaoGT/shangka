package com.geek.shengka.content.constans;

/**
 * @Filename: SearchConstans
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/5 ;
 */
public class SearchConstans {
    /**声咖热搜*/
    public static final String SEARCH_CODE_HOT="hot_search";
    /**人气榜单*/
    public static final String SEARCH_CODE_POPU="popular_rank";
    /**最热视频*/
    public static final String SEARCH_CODE_HOTEST = "hotest_video";
    /**发现精彩*/
    public static final String SEARCH_CODE_FIND="find_wonder";
    /**视频*/
    public static final Integer MODULE_TYPE_1 = 1;
    /**用户*/
    public static final Integer MODULE_TYPE_2 = 2;
    /**话题*/
    public static final Integer MODULE_TYPE_3 = 3;
    /**默认page参数*/
    public static final Integer DEFAULT_PAGE_START = 0;
    public static final Integer DEFAULT_PAGE_COUNT = 10;
    /**默认1分钟*/
    public static final long DEFAULT_REDIS_TIME = 1000 * 60;

    /**redis key*/
    public static final String SEARCH_HOME_PAGE_DATA = "shengka:search:page:data_%s";
    public static final String SEARCH_MODULE_CODES="shengka:search:page:moduleCode_%s";

    /**内容中心默认起始页*/
    public static Integer default_pageNo=1;
    public static Integer default_pageSize_10=10;
}
