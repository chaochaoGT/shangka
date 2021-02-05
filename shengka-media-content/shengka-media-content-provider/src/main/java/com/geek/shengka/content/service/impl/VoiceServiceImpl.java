package com.geek.shengka.content.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.common.enums.UserBaseTypeEnum;
import com.geek.shengka.common.mq.UserBaseDataMsg;
import com.geek.shengka.common.util.CdnUrlUtils;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.content.entity.SkCommentNotice;
import com.geek.shengka.content.entity.SkFans;
import com.geek.shengka.content.entity.SkThumbsUp;
import com.geek.shengka.content.entity.SkUserBaseInfo;
import com.geek.shengka.content.entity.SkVoice;
import com.geek.shengka.content.mapper.SkCommentNoticeDAO;
import com.geek.shengka.content.mapper.SkFansDAO;
import com.geek.shengka.content.mapper.SkThumbsUpDAO;
import com.geek.shengka.content.mapper.SkUserBaseInfoDAO;
import com.geek.shengka.content.mapper.SkVoiceDAO;
import com.geek.shengka.content.proxy.ContentRemoteProxy;
import com.geek.shengka.content.proxy.ContentVerifyRemoteProxy;
import com.geek.shengka.content.rabbitmq.ContentRabbitmqSender;
import com.geek.shengka.content.request.AudioSourceDo;
import com.geek.shengka.content.request.ContentDeleteVoiceRequest;
import com.geek.shengka.content.request.ContentFriendMqRequest;
import com.geek.shengka.content.request.PublishVoice;
import com.geek.shengka.content.request.VoiceAttend;
import com.geek.shengka.content.response.VoiceInfo;
import com.geek.shengka.content.response.VoiceParam;
import com.geek.shengka.content.request.ContentVoiceRequest;
import com.geek.shengka.content.service.VoiceService;
import com.geek.shengka.content.utils.UuidTools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 语音查询
 */
@Service
public class VoiceServiceImpl  implements VoiceService {
	private static final Logger logger = LoggerFactory.getLogger(VoiceServiceImpl.class);
 
  	@Autowired
  	private SkVoiceDAO skVoiceDAO;
    @Autowired
    private ContentVerifyRemoteProxy contentVerifyRemoteProxy;
	@Autowired
	private ContentRabbitmqSender contentRabbitmqSender;
	@Autowired
	private ContentRemoteProxy contentRemoteProxy;
	@Autowired
	private  SkFansDAO skFansDAO;
	@Autowired
	private SkThumbsUpDAO skThumbsUpDAO;
    @Autowired
    private SkUserBaseInfoDAO skUserBaseInfoDAO;
 
   
	@Override
	public  List<VoiceInfo> getRemoteVoices(ContentVoiceRequest param) {
		List<VoiceInfo> voiceInfos = new ArrayList<VoiceInfo>();
        long userId = UserContextHolder.getUserIdByHeader();

        List<String>  fans = null;
        List<String>  thumpsUps = null;
        List<VoiceInfo> voiceInfoDbs = skVoiceDAO.selectByVoices(param.getVideoId(),param.getPageNumber());
        if(userId>=1L) {
        	List<String> attentionUserIds = new ArrayList<String>();
	        List<String> voiceIds = new ArrayList<String>();
		     if(CollectionUtils.isNotEmpty(voiceInfoDbs)) {
				for(VoiceInfo voiceInfoBean:voiceInfoDbs) {
					attentionUserIds.add(String.valueOf(voiceInfoBean.getUserId()));
					voiceIds.add(voiceInfoBean.getAudioId());
				}
		     }
		     if(CollectionUtils.isNotEmpty(attentionUserIds)) {
		    	 fans= skFansDAO.selectByVoiceFans(userId, attentionUserIds);
		     }
		     if(CollectionUtils.isNotEmpty(voiceIds)) {
		    	 thumpsUps = skThumbsUpDAO.selectByVoiceThumbsUps(userId, voiceIds);
		     }
        }

        if(CollectionUtils.isNotEmpty(voiceInfoDbs)) {
			for(VoiceInfo voiceInfoBean:voiceInfoDbs) {
				VoiceInfo voiceInfo = new VoiceInfo();
				voiceInfo.setAudioId(voiceInfoBean.getAudioId());
				voiceInfo.setDuration(voiceInfoBean.getDuration());
				voiceInfo.setNickname(voiceInfoBean.getNickname());
				voiceInfo.setUserId(voiceInfoBean.getUserId());
				voiceInfo.setGeographic(voiceInfoBean.getGeographic());
				voiceInfo.setVideoId(voiceInfoBean.getVideoId());

				voiceInfo.setUrl(CdnUrlUtils.transferCdn(voiceInfoBean.getUrl()));
				voiceInfo.setAvatar(CdnUrlUtils.transferCdn(voiceInfoBean.getAvatar()));

				try {
			        if(userId<=0) {
			        	voiceInfo.setOwnFlag(1);
			        	voiceInfo.setThumbsUpFlag(1);
			        	voiceInfo.setAttendFlag(1);
			        }else {
							if(userId==voiceInfoBean.getUserId()) {
								voiceInfo.setOwnFlag(0);
							}else {
								voiceInfo.setOwnFlag(1);
							}
							 
							if(CollectionUtils.isNotEmpty(fans)) {
								if(fans.contains(userId+"-"+voiceInfo.getUserId())) {
									voiceInfo.setAttendFlag(0);
								}else {
									voiceInfo.setAttendFlag(1);
								}
							}
							
		                   if(CollectionUtils.isNotEmpty(thumpsUps)) {
								if(thumpsUps.contains(userId+"-"+voiceInfo.getAudioId())) {
									voiceInfo.setThumbsUpFlag(0);
								}else {
									voiceInfo.setThumbsUpFlag(1);
								}
		                   }
			        }
				}catch(Exception e) {
					logger.error("转化ownFlag,attendFlag,thumbsUpFlag出现异常");
				}
				
				voiceInfos.add(voiceInfo);
			}
		}
	     
	     
	     
	     
	   if(CollectionUtils.isEmpty(voiceInfos)) {
		   return null;
	   }
		 
 		return voiceInfos;
	}

	@Override
	public String publishVoice(PublishVoice param) {
		String uuid = UuidTools.getUUIDString();
		try{SkVoice skVoice = new SkVoice();
		BeanUtils.copyProperties(param, skVoice);
		
		skVoice.setId(uuid);
		skVoice.setUserId(UserContextHolder.getUserIdByHeader());
	     skVoiceDAO.insertSelective(skVoice);
	    
 		//消费者， 维护用户冗余基础数据 MQ语音
// 		UserBaseDataMsg messageData = new UserBaseDataMsg();
// 		messageData.setUserId(UserContextHolder.getUserIdByHeader());
// 		messageData.setNum(1);
// 		messageData.setCode(UserBaseTypeEnum.VOICE_NUM.getCode());
// 		contentRabbitmqSender.sendUserActionMessage(messageData);
	 		SkUserBaseInfo skUserBaseInfo = new SkUserBaseInfo();
	 		skUserBaseInfo.setUserId(UserContextHolder.getUserIdByHeader());
	 		skUserBaseInfo.setVoiceNum(1);
	 		skUserBaseInfoDAO.updateUserBaseContentNum(skUserBaseInfo);
 		
	    //调用内容审核中心
	    try {
		    AudioSourceDo audioSourceDo  = new AudioSourceDo();
		    audioSourceDo.setAudioId(skVoice.getId());
		    audioSourceDo.setVideoId(skVoice.getVideoId());
		    audioSourceDo.setUrl(skVoice.getVoiceUrl());
		    audioSourceDo.setUserId(String.valueOf(UserContextHolder.getUserIdByHeader()));
		    audioSourceDo.setDuration(skVoice.getDuration());
		    audioSourceDo.setGeographic(skVoice.getGeographic());
			contentVerifyRemoteProxy.uploadAudio(audioSourceDo);
	    }catch(Exception e) {
	    	logger.error(e.getMessage());
	    }
		
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
 		
	    return uuid;
	}

	@Override
	public int delVoice(String  voiceId) {
			 skVoiceDAO.deleteByPrimaryKey(voiceId);
			 ContentDeleteVoiceRequest contentDeleteVoiceRequest = new ContentDeleteVoiceRequest();
			 contentDeleteVoiceRequest.setAudioId(voiceId);
			 contentDeleteVoiceRequest.setStatus("2");
			 contentRemoteProxy.deleteRemoteVoices(contentDeleteVoiceRequest);
        return 0;
	}

	@Override
	public int attentionFriends(VoiceAttend param) {
		String[] noticeUserIds = param.getNoticeUserIds().split(",");
		for(int i=0;i<noticeUserIds.length;i++) {
			long noticeUserId = Long.valueOf(noticeUserIds[i]);
	 		//通知MQ语音
	 		ContentFriendMqRequest messageNotice = new ContentFriendMqRequest();
	 		messageNotice.setType(3);
	 		messageNotice.setMediaId(param.getVoiceId());
	 		messageNotice.setUserId(noticeUserId);
	 		contentRabbitmqSender.sendFriendMessage(messageNotice);
		}
 		return  0;
 		
	}
	 
	

}
