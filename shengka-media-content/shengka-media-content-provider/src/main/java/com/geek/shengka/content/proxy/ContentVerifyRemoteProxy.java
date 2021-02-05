package com.geek.shengka.content.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.http.ContextTools;
import com.geek.shengka.common.http.HttpPoolProxy;
import com.geek.shengka.common.proxy.ContentVerifyProxy;
import com.geek.shengka.common.response.ContentCenterResponse;
import com.geek.shengka.content.request.AudioSourceDo;
import com.geek.shengka.content.request.MediaVideoSource;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * 内容审核中心代理
 * 
 * @author xuxuelei
 */
@Component
@Data
@ConfigurationProperties(prefix = "contentverify")
public class ContentVerifyRemoteProxy {
	private static final Logger logger = LoggerFactory.getLogger(ContentVerifyRemoteProxy.class);
    @Autowired
    private ContentVerifyProxy contentVerifyProxy;
	
	/** 内容审核地址**/	
	private String contentverifyCenterUrl;
    /** 内容审核-视频上传*/	
	private String uploadVideoMethod;
    /** 内容审核-语音同步**/	
	private String uploadAudioMethod;
	
    
	
	private static final String CONTENT_MEDIA_KEY = "CONTENT:MEDIA:%s";
	
	private static final String ROLLING_MEDIA_KEY = "ROLLING:CONTENT:MEDIA:%s";
	


	/**
	 * 上传内容审核中心视频
	 */
	public void uploadVideo(MediaVideoSource mediaVideoSource) {
			try {
				ContentCenterResponse ContentCenterResponse = contentVerifyProxy.getAuthToken();
				if(null==ContentCenterResponse) {
					return ;
 				}
				
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("authorization", ContentCenterResponse.getToken());
				headers.put("bizCode", ContentCenterResponse.getBizCode());
				headers.put("version", "1.1.0");
				  
				String responseData = HttpPoolProxy.postJson(contentverifyCenterUrl+uploadVideoMethod, JSON.toJSONString(mediaVideoSource), 700, 700, 1000, headers);
				if (StringUtils.isNotBlank(responseData)) {
					try {
						BaseResponse response=JSON.parseObject(responseData,new TypeReference<BaseResponse>(){});
						if(response.getCode()!=0) {
							logger.error("上传视频，调用内容审核中心有问题，请找相关内容审核中心人员，请求头:{},参数:{},返回：{}",JSON.toJSONString(headers),JSON.toJSONString(mediaVideoSource),responseData);
						}
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
	
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
	
	/**
	 * 上传内容审核中心语音
	 */
	public void uploadAudio(AudioSourceDo audioSourceDo) {
			try {
				ContentCenterResponse ContentCenterResponse = contentVerifyProxy.getAuthToken();
				if(null==ContentCenterResponse) {
					return  ;
				}
				
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("authorization", ContentCenterResponse.getToken());
				headers.put("bizCode", ContentCenterResponse.getBizCode());
//				headers.put("version", ContextTools.getRequest().getHeader("version"));
				headers.put("version", "1.1.0");
				 
	             
	
				String responseData = HttpPoolProxy.postJson(contentverifyCenterUrl+uploadAudioMethod, JSON.toJSONString(audioSourceDo), 700, 700, 1000, headers);
				if (StringUtils.isNotBlank(responseData)) {
					try {
						BaseResponse response=JSON.parseObject(responseData,new TypeReference<BaseResponse>(){});
						if(response.getCode()!=0) {
							logger.error("上传语音，调用内容审核中心有问题，请找相关内容审核中心人员，请求头:{},参数:{},返回：{}",JSON.toJSONString(headers),JSON.toJSONString(audioSourceDo),responseData);
						}
						
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
	
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
 
 
 
	 
}
