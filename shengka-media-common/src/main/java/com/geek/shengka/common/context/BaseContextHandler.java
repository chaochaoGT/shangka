package com.geek.shengka.common.context;


import com.geek.shengka.common.constant.CommonConstants;
import com.geek.shengka.common.util.StringHelper;

import java.util.HashMap;
import java.util.Map;


public class BaseContextHandler {

    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static String getToken() {
        Object value = get(CommonConstants.CONTEXT_KEY_TOKEN);
        return StringHelper.getObjectValue(value);
    }



    public static String getXName() {
        Object value = get(CommonConstants.CONTEXT_KEY_NAME);
        return StringHelper.getObjectValue(value);
    }

    public static String getNickname() {
        Object value = get(CommonConstants.CONTEXT_KEY_NICKNAME);
        return StringHelper.getObjectValue(value);
    }

    public static String getPlatformId() {
        Object value = get(CommonConstants.CONTEXT_KEY_PLATFORMID);
        return StringHelper.getObjectValue(value);
    }

    public static String getCurrentUId() {
        Object value = get(CommonConstants.CONTEXT_KEY_UID);
        return StringHelper.getObjectValue(value);
    }



    public static String getCurrentPhoneNum() {
        Object value = get(CommonConstants.CONTEXT_KEY_PHONENUM);
        return StringHelper.getObjectValue(value);
    }
    public static String getCurrentAppId() {
        Object value = get(CommonConstants.CONTEXT_KEY_APPID);
        return StringHelper.getObjectValue(value);
    }




    public static String getCurrentSource() {
        Object value = get(CommonConstants.CONTEXT_KEY_SOURCE);
        return StringHelper.getObjectValue(value);
    }
    public static void setToken(String token) {
        set(CommonConstants.CONTEXT_KEY_TOKEN, token);
    }

    public static void setPlatformId(String platformId) {
        set(CommonConstants.CONTEXT_KEY_PLATFORMID, platformId);
    }

    public static void setXName(String name) {
        set(CommonConstants.CONTEXT_KEY_NAME, name);
    }

    public static void setNickname(String name) {
        set(CommonConstants.CONTEXT_KEY_NICKNAME, name);
    }



    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static void seUid(Long uid) {
        set(CommonConstants.CONTEXT_KEY_UID, uid);
    }


    public static void setAppId(String appId) {
        set(CommonConstants.CONTEXT_KEY_APPID, appId);
    }


    public static void setPhoneNum(String phoneNum) {
        set(CommonConstants.CONTEXT_KEY_PHONENUM, phoneNum);
    }
}
