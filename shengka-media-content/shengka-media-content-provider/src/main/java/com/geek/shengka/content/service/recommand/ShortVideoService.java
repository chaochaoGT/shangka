package com.geek.shengka.content.service.recommand;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.geek.shengka.content.config.RecommendConfig;
import com.geek.shengka.content.enums.RecommendEnum;
import com.geek.shengka.content.mapper.SkPublishVideoDAO;
import com.github.pagehelper.PageHelper;

@Service
public class ShortVideoService  implements VideoService{

	private static final Logger logger = LoggerFactory.getLogger(ShortVideoService.class);
	
	@Autowired
	private SkPublishVideoDAO skPublishVideoDAO;
	
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private RecommendConfig recommendConfig;
	
	@Override
	public void initTotal(Long categoryId) {
		Set<String> totalIds =new HashSet<String>();
		totalIds=getByCreateTimeDesc(recommendConfig.getRedisTotalNum());
		redisService.addMembers(RecommendEnum.SHORTVIDEOTOTAL.getKey(), totalIds,RecommendEnum.SHORTVIDEOTOTAL.getExpireTime());
	}
	
	private Set<String> getByCreateTimeDesc(int num) {
    	Set<String> ids =new HashSet<String>();
		for(int i=1;i<4;i++) {
	    	PageHelper.startPage(i,num);
	    	List<String> videoIds=skPublishVideoDAO.selectByCategoryAndWatchMode(0, null);

	    	if(!CollectionUtils.isEmpty(videoIds)) {
	        	ids.addAll(videoIds);
	    	}else {
	    		logger.error("初始化短视频总库时，数据库中数量不足");
	    	}
		}
		return ids;
	}

}
