package com.geek.shengka.gold.util;

import com.geek.shengka.common.cache.CacheProvider;

import java.util.Set;

/**
 * 清除缓存key
 *
 * @author: yunfei
 * @create: 2019-06-14 15:50
 **/
public class ClearRediskeyUtil {

    private ClearRediskeyUtil(){
    }

    private static final String FLOW_USER_CACHE_KEY = "sk:userflow:userid-%s";

    /***
     * 增加金币时，清除流水缓存
     * @param userId
     */
    public static final void clearCachekey(long userId) {
        String localKey = String.format(FLOW_USER_CACHE_KEY, userId);
        Set<String> keys = CacheProvider.getTemplate().keys(localKey + "**");
        for (String key : keys) {
            CacheProvider.del(key);
        }
    }

}
