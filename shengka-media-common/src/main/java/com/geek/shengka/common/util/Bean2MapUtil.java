package com.geek.shengka.common.util;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Bean2MapUtil {
	
	public static Map<String, Object> objectToMap (Object obj) {
        if (obj == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mappedObject = objectMapper.convertValue(obj, Map.class);

        return mappedObject;
    }

}
