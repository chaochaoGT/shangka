package com.geek.shengka.common.cache;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
　*　@description https://www.jianshu.com/p/7bf5dc61ca06/
**/


public class CacheProvider {
	private static final Logger logger = LoggerFactory.getLogger(CacheProvider.class);
	
	//由于当前class不在spring boot框架内（不在web项目中）所以无法使用autowired，使用此种方法进行注入

	private static RedisTemplate<String, String> template = (RedisTemplate<String, String>) SpringBeanUtil.getBean("redisTemplate");
    
	
	/**
	 * 
	 * @return
	 */
	public static RedisTemplate<String, String> getTemplate(){
		return CacheProvider.template;
	}
	
	//默认失效时间60s
	//public static final long DEFAULT_EXPIRED_TIME = 60L;
	
	//临时改成2秒失效，配合测试工作
	//默认失效时间60s
	public static final long DEFAULT_EXPIRED_TIME = 1000 * 60L;
 
	/**
         * 缓存设置对象数据
	 * @param key
	 * @param clazz
	 * @return
	 */
    public static <T> boolean set(String key, T value) {
    	return setObject(key, value, DEFAULT_EXPIRED_TIME);
    }

	/**
	 * 存储list
	 * @param key
	 * @param list
	 */
	public static synchronized void  setList(String key, List<String> list){
		remove(key);
		template.opsForList().rightPushAll(key, list);
	}

	/**
	 *  删除缓存
	 */
	public static void remove(final String key){
		if (exists(key)){
			template.delete(key);
		}
	}

	/**
	 * 获取list
	 * @param key
	 * @return
	 */
	public static List<String> getList(String key){
		return template.opsForList().range(key, 0, -1);
	}





	/**
	 *  判断缓存中是否有对应的value
	 */
	public static  boolean exists(final String key){ return template.hasKey(key);}


    
    /**
	  * 缓存设置对象数据
	 * @param key
	 * @param clazz
	 * @param expire 毫秒数
	 * @return
	 */
    public static <T> boolean setObject(String key, T value, long expire) {
    	if(value != null) {
    		try {
    			String json = JSON.toJSONString(value);
    			//将json对象转换为字符串
    			return CacheProvider.set(key, json, expire);
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    		}
    	}
    	return false;
    }
    
    /**
         *  缓存获取对象数据
     * @param key
     * @param clazz
     * @return
     */
    public static <T> T getObject(String key, Class<T> clazz) {
    	if(StringUtils.isNotBlank(key)) {
    		try {
    			String localStr = get(key);
    			if(StringUtils.isNotBlank(localStr)) {
    				//logger.info("[access-data-via-cache][key="+key+"][data="+localStr+"]");
    				T result = JSON.parseObject(localStr, clazz);
    				//logger.info("[access-obj-via-cache][key="+key+"][data="+localStr+"][obj="+JSON.toJSONString(result)+"]");
    				return result;
    			}else {
    				//logger.info("[access-data-via-cache-empty][key="+key+"]");
    			}
    		} catch (Exception e) {
    			logger.error(e.getMessage(), e);
    		}
    	}
    	return null;
    }
   

    
    public static boolean set(String key, String value) {
    	return  set(key, value, DEFAULT_EXPIRED_TIME);
    }
    
   
    public static String get(String key) {
    	return template.opsForValue().get(key);
    }

    public static boolean del(String key) {
        return template.delete(key);
    }

    /**
     * validTime  毫秒数
     * @param key
     * @param value
     * @param validTime
     * @return
     */
    public static boolean set(String key, String value, long validTime) {
        try {
			template.opsForValue().set(key, value);
			template.expire(key, validTime, TimeUnit.MILLISECONDS);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
        return false;
    }
}
