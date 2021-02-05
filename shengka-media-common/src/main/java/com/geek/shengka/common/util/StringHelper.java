package com.geek.shengka.common.util;


import org.apache.commons.collections.MapUtils;

import java.util.Map;

public class StringHelper {
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }
    public static String getStringValue(Map map,String key){
        return MapUtils.isEmpty(map)?"":(String)map.get(key);
    }
    public static Integer getIntValue(Map map,String key){
        if(MapUtils.isEmpty(map)){
             throw new IllegalArgumentException("参数不能为空");
        }
        return(Integer)map.get(key);
    }

}
