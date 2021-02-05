package com.geek.shengka.content.service.recommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class RedisService {

	@Autowired	
    private RedisTemplate<String,String> redisTemplate;
	
    public boolean isExsits(String key) {
    	String member=redisTemplate.opsForSet().randomMember(key);
    	if(StringUtils.isNotEmpty(member)) {
    		return true;
    	}
    	return false;
    }
    
    public boolean del(String key){
    	return redisTemplate.delete(key);
    }
    
    public Set<String> members(String key){
    	Set<String> members = redisTemplate.opsForSet().members(key);
    	return members;
    }
    
    public long addMember(String key,String value,long timeout) {
    	if(StringUtils.isNotBlank(value)) {
    		long count=redisTemplate.opsForSet().add(key,value);
    		redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    		return count;
    	}
    	return 0L;
    }
	
    public long addMembers(String key,Set<String> values,long timeout) {
    	if(!CollectionUtils.isEmpty(values)) {
    		long count=redisTemplate.opsForSet().add(key,values.toArray(new String[values.size()]));
    		redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    		return count;
    	}
    	return 0L;
    }
    
    public long interstore(String key, String otherKey, String destKey ,long timeout) {
    	long count=redisTemplate.opsForSet().intersectAndStore(key, otherKey, destKey);
		redisTemplate.expire(destKey, timeout, TimeUnit.MILLISECONDS);
		return count;
	}
    
    public long diffstore(String key, String otherKey, String destKey ,long timeout) {
    	long count= redisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey);
		redisTemplate.expire(destKey, timeout, TimeUnit.MILLISECONDS);
		return count;
	}
    
    public long diffTwostore(String key, String otherOneKey,String otherTwoKey, String destKey ,long timeout) {
    	Collection<String> otherKeys= new ArrayList<String>();
    	if(StringUtils.isNotEmpty(otherOneKey)) {
    		otherKeys.add(otherOneKey);
    	}
    	if(StringUtils.isNotEmpty(otherTwoKey)) {
    		otherKeys.add(otherTwoKey);
    	}
    	long count=redisTemplate.opsForSet().differenceAndStore(key,otherKeys, destKey);
		redisTemplate.expire(destKey, timeout, TimeUnit.MILLISECONDS);
		return count;
	}
    
    public long unionStore(String key,String destKey ,long timeout) {
    	long count=redisTemplate.opsForSet().unionAndStore(key, destKey, destKey);
		redisTemplate.expire(destKey, timeout, TimeUnit.MILLISECONDS);
		return count;
	}
    
    public Set<String> randomMembers(String key,int count){
    	 return redisTemplate.opsForSet().distinctRandomMembers(key,count);
    }
    
    public boolean isMembers(String key,String value){
   	 return redisTemplate.opsForSet().isMember(key, value);
   }
    
}
