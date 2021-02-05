package com.geek.shengka.content.service;

import java.util.List;

import com.geek.shengka.content.request.ContentBackRequest;
import com.geek.shengka.content.request.ContentDeleteVideoRequest;
import com.geek.shengka.content.request.ContentMediaRequest;
import com.geek.shengka.content.request.PublishMediaRequest;
import com.geek.shengka.content.response.MediaDetailInfo;
import com.geek.shengka.content.response.MediaInfo;

/**
 *   视频接口
 */
public interface MediaService {
	
	/**首页视频内容 **/
	 List<MediaInfo> getRemoteVideos(ContentMediaRequest param);
	 
	/**视频详情内容 **/
	 MediaDetailInfo getRemoteVideoDetail(String videoId);
	
	/**视频发布 **/
	 int publishMedia(PublishMediaRequest param);
     
  	/** 视频删除**/
  	int delVideo(String videoId);
	
	/**内容 中心回调 **/
	 int syncMeidaInfo(ContentBackRequest param);
	 
}
