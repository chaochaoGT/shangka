package com.geek.shengka.content.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.common.http.ContextTools;
import com.geek.shengka.common.http.HttpPoolProxy;
import com.geek.shengka.common.proxy.ContentCenterProxy;
import com.geek.shengka.common.proxy.ContentUrlConfig;
import com.geek.shengka.common.response.ContentCenterResponse;
import com.geek.shengka.content.constans.RedisContentConstants;
import com.geek.shengka.content.request.ContentDeleteVideoRequest;
import com.geek.shengka.content.request.ContentDeleteVoiceRequest;
import com.geek.shengka.content.request.ContentDetailVideoRequest;
import com.geek.shengka.content.request.ContentVoiceRequest;
import com.geek.shengka.content.response.ContentVoiceInfo;
import com.geek.shengka.content.response.MediaDetailInfo;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内容中心代理
 * 
 * @author xuxuelei
 */
@Component
@Data
public class ContentRemoteProxy {
	private static final Logger logger = LoggerFactory.getLogger(ContentCenterProxy.class);
    @Autowired
    private ContentCenterProxy contentCenterProxy;

    @Autowired
    private ContentUrlConfig contentUrlConfig;
     // 默认失效时间：2s
    private static final long expireTime = 1000 * 10L; 
 
    // 默认失效时间：1s
   private static final long expireOneTime = 1000 * 10L; 
//	/**
//	 * 获取内容中心视频列表
//	 *
//	 * @param param ---视频id
//	 * @return
//	 */
//	public List<ContentMediaInfo> getRemoteVideos(ContentMediaRequest param) {
//		 List<ContentMediaInfo> contentMediaInfos  = null;
//		try {
//			 if(CollectionUtils.isEmpty(contentMediaInfos)) {
//				param.setNeedCdnPrefix("true");
//				if(param.getPageSize()==0) {
//					param.setPageSize(5);
//				}
//				ContentCenterResponse ContentCenterResponse = contentCenterProxy.getAuthToken();
//				if(null==ContentCenterResponse) {
//					return null;
//				}
//				
//				Map<String, String> headers = new HashMap<String, String>();
//				headers.put("authorization", ContentCenterResponse.getToken());
//				headers.put("bizCode", ContentCenterResponse.getBizCode());
//				headers.put("version", ContextTools.getRequest().getHeader("version"));
//                   
//				String responseData = HttpPoolProxy.postJson(contentUrlConfig.getContentCenterUrl()+contentUrlConfig.getContentVideosMethod(), JSON.toJSONString(param), 700, 700, 1000, headers);
//				
//				if (StringUtils.isNotBlank(responseData)) {
//					try {
//						  JSONObject data = (JSONObject) JSON.parseObject(responseData, BaseResponse.class).getData();
//							if(StringUtils.equals(data.getString("code"),"0")) {
//								logger.error("获取视频列表时，调用内容中心有问题，请找相关内容中心人员，请求头:{},参数:{},返回：{}",JSON.toJSONString(headers),JSON.toJSONString(param),responseData);
//							}
//							try {
//		                       contentMediaInfos = JSON.parseArray(data.getString("list"), ContentMediaInfo.class);
////		                       CacheProvider.setObject(RedisContentConstants.CONTENT_VIDEO, contentMediaInfos, expireTime);
//							}catch(Exception e) {
//								contentMediaInfos = null;
//							}
//						return contentMediaInfos;
//						
//					} catch (Exception e) {
//						logger.error(e.getMessage(), e);
//					}
//				}
//			 } 
//
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//
//		return contentMediaInfos;
//	}
//	
	
	
	/**
	 * 视频语音评论查询
	 *  根据视频id获取语音列表
	 * @param  
	 * @return
	 */
	public List<ContentVoiceInfo> getRemoteVoices(ContentVoiceRequest param) {
		 List<ContentVoiceInfo> contentVoiceInfos  = null;
         
		try {
			 if(CollectionUtils.isEmpty(contentVoiceInfos)) {
				if(param.getPageSize()==0) {
					param.setPageSize(5);
				}
				ContentCenterResponse ContentCenterResponse = contentCenterProxy.getAuthToken();
				if(null==ContentCenterResponse) {
					return null;
				}
				
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("authorization", ContentCenterResponse.getToken());
				headers.put("bizCode", ContentCenterResponse.getBizCode());
				headers.put("version","1.1.0");
                   
				String responseData = HttpPoolProxy.postJson(contentUrlConfig.getContentCenterUrl()+contentUrlConfig.getContentVoicesMethod(), JSON.toJSONString(param), 700, 700, 1000, headers);
				
				if (StringUtils.isNotBlank(responseData)) {
					try {
						  JSONObject data = (JSONObject) JSON.parseObject(responseData, BaseResponse.class).getData();
							if(StringUtils.equals(data.getString("code"),"0")) {
								logger.error("获取视频语音列表，调用内容中心有问题，请找相关的内容中心人员，请求头:{},参数:{},返回：{}",JSON.toJSONString(headers),JSON.toJSONString(param),responseData);
							}
							try {
		                      contentVoiceInfos = JSON.parseArray(data.getString("list"), ContentVoiceInfo.class);
//		                      CacheProvider.setObject(RedisContentConstants.CONTENT_VOICE, contentVoiceInfos, expireTime);
							}catch(Exception e) {
								contentVoiceInfos = null;
							}
						return contentVoiceInfos;
						
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			 }
			 

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return contentVoiceInfos;
	}
	
	
	/**
	 * 视频详情
	 *   
	 * @param  
	 * @return
	 */
	public MediaDetailInfo getRemoteVideoDetail(ContentDetailVideoRequest param) {
		MediaDetailInfo mediaDetailInfo  = null;
		try {
			 if(null==mediaDetailInfo) {
				ContentCenterResponse ContentCenterResponse = contentCenterProxy.getAuthToken();
				if(null==ContentCenterResponse) {
					return null ;
				}
				
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("authorization", ContentCenterResponse.getToken());
				headers.put("bizCode", ContentCenterResponse.getBizCode());
				headers.put("version", "1.1.0");
                   
				String responseData = HttpPoolProxy.postJson(contentUrlConfig.getContentCenterUrl()+contentUrlConfig.getMultiConditionMedias(), JSON.toJSONString(param), 700, 700, 1000, headers);
				
				if (StringUtils.isNotBlank(responseData)) {
					try {
							BaseResponse response=JSON.parseObject(responseData,new TypeReference<BaseResponse>(){});
							if(response.getCode()!=0) {
								logger.error("视频详情，调用内容中心有问题，请找相关的内容中心人员，请求头:{},参数:{},返回：{}",JSON.toJSONString(headers),JSON.toJSONString(responseData),responseData);
							}
							List<MediaDetailInfo> mediaDetailInfos   =  JSON.parseArray(JSON.toJSONString(response.getData()),MediaDetailInfo.class);
							 if(CollectionUtils.isNotEmpty(mediaDetailInfos)) {
 									return  mediaDetailInfos.get(0);
							 }
							  
					
						
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			 }
			 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return mediaDetailInfo;
	}
	
	
	
	/**
	 * 视频删除
	 *   
	 * @param  
	 * @return
	 */
	public void deleteRemoteVideos(ContentDeleteVideoRequest param) {
		try {
			 
				ContentCenterResponse ContentCenterResponse = contentCenterProxy.getAuthToken();
				if(null==ContentCenterResponse) {
					return  ;
				}
				
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("authorization", ContentCenterResponse.getToken());
				headers.put("bizCode", ContentCenterResponse.getBizCode());
				headers.put("version", ContextTools.getRequest().getHeader("version"));
                   
				String responseData = HttpPoolProxy.postJson(contentUrlConfig.getContentCenterUrl()+contentUrlConfig.getContentDeleteVideosMethod(), JSON.toJSONString(param), 700, 700, 1000, headers);
				
				if (StringUtils.isNotBlank(responseData)) {
					try {
						  JSONObject data = (JSONObject) JSON.parseObject(responseData, BaseResponse.class).getData();
							if(StringUtils.equals(data.getString("code"),"0")) {
								logger.error("视频删除，调用内容中心有问题，请找相关内容中心人员，请求头:{},参数:{},返回：{}",JSON.toJSONString(headers),JSON.toJSONString(param),responseData);
							}
	        
						return ;
						
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return ;
	}
	
	
	
	/**
	 * 语音删除
	 *   
	 * @param  
	 * @return
	 */
	public void deleteRemoteVoices(ContentDeleteVoiceRequest param) {
		try {
			 
				ContentCenterResponse ContentCenterResponse = contentCenterProxy.getAuthToken();
				if(null==ContentCenterResponse) {
					return  ;
				}
				
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("authorization", ContentCenterResponse.getToken());
				headers.put("bizCode", ContentCenterResponse.getBizCode());
				headers.put("version", ContextTools.getRequest().getHeader("version"));
                   
				String responseData = HttpPoolProxy.postJson(contentUrlConfig.getContentCenterUrl()+contentUrlConfig.getContentDeleteVoicesMethod(), JSON.toJSONString(param), 700, 700, 1000, headers);
				
				if (StringUtils.isNotBlank(responseData)) {
					try {
						  JSONObject data = (JSONObject) JSON.parseObject(responseData, BaseResponse.class).getData();
							if(StringUtils.equals(data.getString("code"),"0")) {
								logger.error("语音删除，调用内容中心有问题，请找相关内容中心人员，请求头:{},参数:{},返回：{}",JSON.toJSONString(headers),JSON.toJSONString(param),responseData);
							}
	        
						return ;
						
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return ;
	}
 
	 
}
