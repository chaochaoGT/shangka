package com.geek.shengka.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

/**
 * JSON格式操作
 * 
 * @author xuxuelei
 *
 */
public class JsonTools {

	/**
	 * objectToJson object转json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		if (object instanceof Integer || object instanceof Long || object instanceof Float || object instanceof Double
				|| object instanceof Boolean || object instanceof String) {
			return String.valueOf(object);
		}
		return JSON.toJSONString(object);
	}

	/**
	 * jsonToObject json字符串转object
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parseObject(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

	/**
	 * json字符串转list
	 * 
	 * @param jsonData
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonData, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
		try {
			List<T> list = mapper.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * map转object
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> Object mapToObject(Map<String, Object> map, Class<T> clazz) throws Exception {
		if (map == null) {
            return null;
        }
		Object obj = clazz.newInstance();
		org.apache.commons.beanutils.BeanUtils.populate(obj, map);
		return obj;
	}

	/**
	 * object转map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<?, ?> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		return new org.apache.commons.beanutils.BeanMap(obj);
	}

 
	
}
