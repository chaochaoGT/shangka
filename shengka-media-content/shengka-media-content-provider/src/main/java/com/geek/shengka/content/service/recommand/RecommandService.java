package com.geek.shengka.content.service.recommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.geek.shengka.common.basemodel.BaseMediaInfo;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.http.ContextTools;
import com.geek.shengka.common.http.HttpPoolProxy;
import com.geek.shengka.common.proxy.ContentCenterProxy;
import com.geek.shengka.common.proxy.ContentUrlConfig;
import com.geek.shengka.common.proxy.ContentVideoInfoProxy;
import com.geek.shengka.common.request.BaseContentRequest;
import com.geek.shengka.common.response.ContentCenterResponse;
import com.geek.shengka.content.enums.RecommendEnum;
import com.geek.shengka.content.service.recommand.entity.GetNotInsertedParam;
import com.geek.shengka.content.service.recommand.entity.ShortVideoRecommandParam;
import com.geek.shengka.content.service.recommand.entity.SmallVideoRecommandParam;

@Service
public class RecommandService {

	private static final Logger logger = LoggerFactory.getLogger(RecommandService.class);
	@Autowired
	private CategoryShortVideoService categoryShortVideoService;
	@Autowired
	private ShortVideoService shortVideoService;
	@Autowired
	private SmallVideoService smallVideoService;
	@Autowired
	private RedisService redisService;
	
    @Autowired
    private ContentCenterProxy contentProxyV4;

	@Autowired
	private ContentVideoInfoProxy contentVideoInfoProxy;
	
    @Autowired
    private ContentUrlConfig contentUrlConfig;

	public List<BaseMediaInfo> recommandShortVideos(ShortVideoRecommandParam recommandParam) {
		Set<String> notInsertedIds = null;
		GetNotInsertedParam getNotInsertedParam = new GetNotInsertedParam();
		getNotInsertedParam.setImei(recommandParam.getImei());
		getNotInsertedParam.setNum(recommandParam.getPageSize());
		GetNotInsertedService getNotInsertedService=null;
		if (recommandParam.getCategoryId() == null || recommandParam.getCategoryId().compareTo(0L) <= 0) {
			getNotInsertedParam.setTotal(RecommendEnum.SHORTVIDEOTOTAL);
			getNotInsertedParam.setUserDiff(RecommendEnum.USERSHORTVIDEODIFF);
			getNotInsertedParam.setUserInserted(RecommendEnum.USERINSERTEDSHORTVIDEO);
			getNotInsertedService= new GetNotInsertedService(shortVideoService,redisService);
			notInsertedIds = getNotInsertedService.getNotInserted(getNotInsertedParam);
		} else {
			getNotInsertedParam.setTotal(RecommendEnum.CATEGORYSHORTVIDEOTOTAL);
			getNotInsertedParam.setUserDiff(RecommendEnum.USERCATEGORYSHORTVIDEODIFF);
			getNotInsertedParam.setUserInserted(RecommendEnum.USERINSERTEDCATGORYSHORTVIDEO);
			getNotInsertedParam.setCategoryId(recommandParam.getCategoryId());
		    getNotInsertedService = new GetNotInsertedService(categoryShortVideoService,redisService);
			notInsertedIds = getNotInsertedService.getNotInserted(getNotInsertedParam);
		}

		List<BaseMediaInfo> contentMediaInfos = null;
		//缓存失效时或总库已经全部推荐过从总库重新获取视频
		if (CollectionUtils.isEmpty(notInsertedIds)) {
			logger.error("缓存失效或总库已经全部推荐过");
			notInsertedIds=getNotInsertedService.getFromTotal(getNotInsertedParam);
		}
		List<String> ids = new ArrayList<String>();
		ids.addAll(notInsertedIds);
		contentMediaInfos = multiConditionGetMedias(BaseContentRequest.builder().videoIds(ids).pageSize(recommandParam.getPageSize()).build());
		if(CollectionUtils.isEmpty(contentMediaInfos)||contentMediaInfos.size()!=ids.size()) {
			logger.error("多个id查询内容中心，返回数据为空或返回视频个数不对,{}",ids);
		}
		return contentMediaInfos;
	}
	
	
    /**
     * 内容中心-多条件获取视频接口
     * @param param
     */
    public <T extends BaseContentRequest> List<BaseMediaInfo> multiConditionGetMedias(T param) {
        String responseData="";
        try {
            ContentCenterResponse ContentCenterResponse = contentProxyV4.getAuthToken();
            if (null == ContentCenterResponse) {
               return null;
            }
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("authorization", ContentCenterResponse.getToken());
            headers.put("bizCode", ContentCenterResponse.getBizCode());
            headers.put("version", ContextTools.getRequest().getHeader("version"));

            String url = contentUrlConfig.getContentCenterUrl() + contentUrlConfig.getMultiConditionMedias();
            responseData = HttpPoolProxy.postJson(url,JSON.toJSONString(param), 3000, 1000, 1000, headers);
            if (StringUtils.isNotBlank(responseData)) {
                try {
                    BaseResponse<List<BaseMediaInfo>> listBaseResponse = JSON.parseObject(responseData,
                            new TypeReference<BaseResponse<List<BaseMediaInfo>>>() {
                    });
                    return listBaseResponse.getData();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            logger.error("mitiConditionGetMedias responseData={} error={}",responseData, e);
        }
        return null;
    }

	public List<BaseMediaInfo> recommandSmallVideos(SmallVideoRecommandParam recommandParam) {
		Set<String> notInsertedIds = null;
		GetNotInsertedParam getNotInsertedParam = new GetNotInsertedParam();
		getNotInsertedParam.setImei(recommandParam.getImei());
		getNotInsertedParam.setNum(recommandParam.getPageSize());
		getNotInsertedParam.setTotal(RecommendEnum.SMALLVIDEOTOTAL);
		getNotInsertedParam.setUserDiff(RecommendEnum.USERSMALLVIDEODIFF);
		getNotInsertedParam.setUserInserted(RecommendEnum.USERINSERTEDSMALLVIDEO);
		GetNotInsertedService getNotInsertedService = new GetNotInsertedService(smallVideoService,redisService);
		notInsertedIds = getNotInsertedService.getNotInserted(getNotInsertedParam);
		List<BaseMediaInfo> contentMediaInfos = null;
		//缓存失效时或总库已经全部推荐过从总库重新获取视频
		if (CollectionUtils.isEmpty(notInsertedIds)) {
			logger.error("缓存失效或总库已经全部推荐过");
			notInsertedIds=getNotInsertedService.getFromTotal(getNotInsertedParam);
		}
		List<String> ids = new ArrayList<String>();
		ids.addAll(notInsertedIds);
		contentMediaInfos =multiConditionGetMedias(BaseContentRequest.builder().videoIds(ids).pageSize(recommandParam.getPageSize()).build());
		if(CollectionUtils.isEmpty(contentMediaInfos)||contentMediaInfos.size()!=ids.size()) {
			logger.error("多个id查询内容中心，返回数据为空或返回视频个数不对,{}",ids);
		}
		return contentMediaInfos;
	}

}
