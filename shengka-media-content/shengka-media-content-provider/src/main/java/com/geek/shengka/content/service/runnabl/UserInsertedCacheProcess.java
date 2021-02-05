package com.geek.shengka.content.service.runnabl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.geek.shengka.content.enums.RecommendEnum;
import com.geek.shengka.content.service.recommand.RedisService;


public class UserInsertedCacheProcess implements Runnable{

	public final static Logger logger = LoggerFactory.getLogger(UserInsertedCacheProcess.class);
	
	private RedisService recommendRedisService;
	private Set<String> resultIds;	
	private RecommendEnum userInserted;
	private String imei;
	private Long categoryId;
	
	public UserInsertedCacheProcess(RedisService recommendRedisService,Set<String> resultIds,RecommendEnum userInserted,String imei,Long categoryId) {
		this.recommendRedisService=recommendRedisService;
		this.resultIds=resultIds;
		this.userInserted=userInserted;
		this.imei=imei;
		this.categoryId=categoryId;
	}
	
	@Override
	public void run() {
		logger.info("{},{}",String.format(userInserted.getKey(), imei,categoryId),resultIds);
		recommendRedisService.addMembers(String.format(userInserted.getKey(), imei,categoryId), resultIds,userInserted.getExpireTime());
	}
}
