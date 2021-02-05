package com.geek.shengka.content.service.recommand;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.geek.shengka.content.config.RecommendConfig;
import com.geek.shengka.content.enums.RecommendEnum;
import com.geek.shengka.content.mapper.SkCategoryMappingDAO;
import com.geek.shengka.content.mapper.SkPublishVideoDAO;
import com.github.pagehelper.PageHelper;

@Service
public class CategoryShortVideoService implements VideoService{

	private static final Logger logger = LoggerFactory.getLogger(CategoryShortVideoService.class);
	
	@Autowired
	private SkPublishVideoDAO skPublishVideoDAO;
	
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private RecommendConfig recommendConfig;
    
    @Autowired
	private  SkCategoryMappingDAO skCategoryMappingDAO;
    
	@Override
	public void initTotal(Long categoryId) {
		Set<String> totalIds =new HashSet<String>();
		totalIds=getByCreateTimeDesc(categoryId,recommendConfig.getRedisTotalNum());
		redisService.addMembers(String.format(RecommendEnum.CATEGORYSHORTVIDEOTOTAL.getKey(), categoryId), totalIds,RecommendEnum.CATEGORYSHORTVIDEOTOTAL.getExpireTime());
	}
	
	private Set<String> getByCreateTimeDesc(Long categoryId,int num) {
		Assert.notNull(categoryId,"频道id不能为空");
		List<Long> contentCategoryIds= skCategoryMappingDAO.selectContentCategoryIdByCategorId(categoryId);
    	Set<String> ids =new HashSet<String>();
		for(int i=1;i<4;i++) {
			PageHelper.startPage(i,num,"create_time desc");
	    	List<String> videoIds=skPublishVideoDAO.selectByCategoryAndWatchMode(0, contentCategoryIds);
	    	if(!CollectionUtils.isEmpty(videoIds)) {
	        	ids.addAll(videoIds);
	    	}else {
	    		logger.error("初始化分类短视频总库时，数据库中数量不足，分类id：{}",contentCategoryIds);
	    	}
		}
		return ids;
	}

}
