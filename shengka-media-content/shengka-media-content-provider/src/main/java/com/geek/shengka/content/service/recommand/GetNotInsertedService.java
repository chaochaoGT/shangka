package com.geek.shengka.content.service.recommand;

import java.util.Set;

import com.geek.shengka.content.service.recommand.entity.GetNotInsertedParam;
import com.geek.shengka.content.service.runnabl.RecommandThreadExecutor;
import com.geek.shengka.content.service.runnabl.UserInsertedCacheProcess;

public class GetNotInsertedService {
	
    private RedisService recommendRedisService;
	
	private VideoService videoService;
	
	public GetNotInsertedService(VideoService videoService,RedisService recommendRedisService) {
		this.videoService=videoService;
		this.recommendRedisService=recommendRedisService;
	}

	public Set<String> getNotInserted(GetNotInsertedParam recommandParam) {
		if(recommandParam.getCategoryId()==null||recommandParam.getCategoryId()<=0) {
			recommandParam.setCategoryId(null);
		}
		//1  总库是否在redis中
		boolean isTotalExsit=recommendRedisService.isExsits(String.format(recommandParam.getTotal().getKey(), recommandParam.getCategoryId()));
		if(!isTotalExsit) {
			videoService.initTotal(recommandParam.getCategoryId());
		}
		//2  用户redis中的已插入视频记录
		boolean isUserInsertedExsit=recommendRedisService.isExsits(String.format(recommandParam.getUserInserted().getKey(), recommandParam.getImei(),recommandParam.getCategoryId()));
		
		Set<String> resultIds=null;
		if(isUserInsertedExsit) {
		//3 总库和已插入库的差集
			recommendRedisService.diffstore(String.format(recommandParam.getTotal().getKey(), recommandParam.getCategoryId()),String.format(recommandParam.getUserInserted().getKey(), recommandParam.getImei(),recommandParam.getCategoryId()) ,String.format(recommandParam.getUserDiff().getKey(), recommandParam.getImei(),recommandParam.getCategoryId()) ,recommandParam.getUserDiff().getExpireTime());
			//4 差集中随机3个视频
			resultIds=recommendRedisService.randomMembers(String.format(recommandParam.getUserDiff().getKey(), recommandParam.getImei(),recommandParam.getCategoryId()), recommandParam.getNum());
			recommendRedisService.del(String.format(recommandParam.getUserDiff().getKey(), recommandParam.getImei(),recommandParam.getCategoryId()));
		}else {
			resultIds=recommendRedisService.randomMembers(String.format(recommandParam.getTotal().getKey(), recommandParam.getCategoryId()), recommandParam.getNum());
		}
		UserInsertedCacheProcess userInsertedCacheProcess=new UserInsertedCacheProcess(recommendRedisService,resultIds,recommandParam.getUserInserted(),recommandParam.getImei(),recommandParam.getCategoryId());
		//将返回的条数异步加入到已推荐小视频库中
		RecommandThreadExecutor.execute(userInsertedCacheProcess);
		return resultIds;
	}
	
	public Set<String> getFromTotal(GetNotInsertedParam recommandParam) {
		//1  总库是否在redis中
		boolean isTotalExsit=recommendRedisService.isExsits(String.format(recommandParam.getTotal().getKey(), recommandParam.getCategoryId()));
		if(!isTotalExsit) {
			videoService.initTotal(recommandParam.getCategoryId());
		}
		return recommendRedisService.randomMembers(String.format(recommandParam.getTotal().getKey(), recommandParam.getCategoryId()), recommandParam.getNum());
	}
}
