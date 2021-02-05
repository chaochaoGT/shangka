package com.geek.shengka.backend.constant;

/**
 * 常量类
 */
public interface CommonConstant {

    Integer SYS_ERROR = -1;
    String ADD_FAIL = "新增失败";
    String UPDATE_FAIL = "更新失败";
    String DELETE_FAIL = "删除失败";
    String BYPE_WITH_POINT = "Byte.";
    String FILE_PREFIX = "filename/filesize=";

    /**
     * 指定该Object被下载时的网页的缓存行为
     */
    String NO_CACHE = "no-cache";

    /**
     * 请求头
     */
    String PRAGMA = "Pragma";

    /**
     * utf-8
     */
    String UTF8 = "utf-8";

    String POINT = ".";

    String SLASH = "/";

    String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String DATE_TIME_TIMEZONE = "GMT+8";
    /**
    * 请求头 token的 key
    */
    String CONTEXT_KEY_TOKEN = "token";

    int CONTENT_TOKEN_FAILURE_CODE = 888;

    String CONTENT_TOKEN_FAILURE_MSG="TOKEN已失效";


    /**
     * 1
     */
    String ONE_STR = "1";

    /**
     * 2
     */
    String TWO_STR = "2";

    /**
     * 3
     */
    String THREE_STR = "3";
    /**
     * shhet页导入失败提示
     */
    String SHEET_INVALID_FAIL = "名为 《{0}》 的sheet页导入失败";
}
