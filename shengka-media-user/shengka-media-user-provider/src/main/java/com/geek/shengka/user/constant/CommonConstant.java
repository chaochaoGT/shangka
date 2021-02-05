package com.geek.shengka.user.constant;

/**
 * 常量类
 */
public interface CommonConstant {

    Integer SYS_ERROR = -1;
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

    /**
     * redis用户信息缓存key
     */
    String REDIS_KEY_USER_ATTENTION = "sk:user";
    /**
     * redis用户信息缓存key 的过期时间
     */
    Long REDIS_KEY_USER_ATTENTION_TIME = 1 * 60 * 60L;
    /**
     * redis分隔符
     */
    String REDIS_KEY_SPLIT = ":";
    /**
     * redis user 分隔符
     */
    String REDIS_KEY_USER_SPLIT = "-";
    /**
     * redis key 匹配 分隔符
     */
    String REDIS_KEY_PATTERN = "*";
}
