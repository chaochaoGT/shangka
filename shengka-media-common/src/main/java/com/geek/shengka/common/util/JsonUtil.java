package com.geek.shengka.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Author: cuihuayang
 * Date: 2018/12/18 10:52
 * Description:
 */
public class JsonUtil {

    public static String objectToJson(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToObject(String jsonData, Class<T> beanType) {
        try {
            T t = new ObjectMapper().readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = mapper.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <K,V> HashMap<K,V> jsonToHashMap(String jsonData,Class<K> kClass,Class<V> vClass){
        ObjectMapper om = new ObjectMapper();
        JavaType javaType = om.getTypeFactory().constructParametricType(HashMap.class, kClass, vClass);
        try {
            HashMap<K, V> extMap = om.readValue(jsonData, javaType);
            return extMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
