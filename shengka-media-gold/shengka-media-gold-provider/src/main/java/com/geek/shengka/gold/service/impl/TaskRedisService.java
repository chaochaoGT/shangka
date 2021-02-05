package com.geek.shengka.gold.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.gold.api.entity.UserTaskCacheInfo;

@Service
public class TaskRedisService {
	
    public static final String DAILYUSERTASK = "user:%s:dailytask:%s:day:%s";//用户任务
    
    public static final long DAILYUSERTASKTIMEOUT = 25*60*60*1000L;//用户任务超时时间,单位毫秒
    
    public static final String NEWMAN_USERTASK = "user:%s:newmantask:%s";//用户任务
    
    public static final long NEWMAN_USERTASK_TIMEOUT = 30*24*60*60*1000L;//用户任务超时时间,单位毫秒
    
	@Autowired
    private RedisTemplate<String,String> redisTemplate;

	public UserTaskCacheInfo get(String key) {
		String value= redisTemplate.opsForValue().get(key);
		
		UserTaskCacheInfo userTaskCacheInfo=null;
		if(StringUtils.isNotBlank(value)) {
			userTaskCacheInfo = JSON.parseObject(value, UserTaskCacheInfo.class);
		}
		return userTaskCacheInfo;
		
	}
	
	
	public void set(String key,UserTaskCacheInfo userTaskCacheInfo,long timeout) {
		if(userTaskCacheInfo!=null) {
			String json = JSON.toJSONString(userTaskCacheInfo);
			redisTemplate.opsForValue().set(key, json);
			redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
		}
		
	}
    
}
