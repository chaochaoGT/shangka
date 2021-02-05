package com.geek.shengka.content.service.impl;

import com.geek.shengka.common.mq.NoticeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.geek.shengka.common.enums.UserBaseTypeEnum;
import com.geek.shengka.common.mq.UserBaseDataMsg;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.content.entity.SkThumbsUp;
import com.geek.shengka.content.entity.SkUserBaseInfo;
import com.geek.shengka.content.entity.SkVoice;
import com.geek.shengka.content.mapper.SkThumbsUpDAO;
import com.geek.shengka.content.mapper.SkUserBaseInfoDAO;
import com.geek.shengka.content.mapper.SkVoiceDAO;
import com.geek.shengka.content.rabbitmq.ContentRabbitmqSender;
import com.geek.shengka.content.request.ThumbsUpRequest;
import com.geek.shengka.content.service.ThumbsUpService;

/**
 * 语音查询
 */
@Service
public class ThumbsUpServiceImpl  implements ThumbsUpService {
	private static final Logger logger = LoggerFactory.getLogger(ThumbsUpServiceImpl.class);
 
  	@Autowired
  	private  SkThumbsUpDAO skThumbsUpDAO;
	@Autowired
	private ContentRabbitmqSender contentRabbitmqSender;
    @Autowired
    private SkVoiceDAO skVoiceDAO;
    @Autowired
    private SkUserBaseInfoDAO skUserBaseInfoDAO;
    
	@Override
	public int thumbsUpVoice(ThumbsUpRequest param) {
		
		SkVoice skVoice= skVoiceDAO.selectByPrimaryKey(param.getVoiceId());
		SkThumbsUp skThumbsUpBean = new SkThumbsUp();
		skThumbsUpBean.setMediaId(param.getVoiceId());
		skThumbsUpBean.setThumbType((byte) 1);
		skThumbsUpBean.setUserId(UserContextHolder.getUserIdByHeader());
		SkThumbsUp skThumbsUpSelect = skThumbsUpDAO.selectByUnique(skThumbsUpBean);
		if(null!=skVoice) {
			if(0==param.getVoiceType()&&null==skThumbsUpSelect) {
				SkThumbsUp skThumbsUp = new SkThumbsUp();
				skThumbsUp.setThumbType((byte) 1);
				skThumbsUp.setUserId(UserContextHolder.getUserIdByHeader());
				skThumbsUp.setMediaId(param.getVoiceId());
				skThumbsUp.setMediaAuthorId(skVoice.getUserId());
			       skThumbsUpDAO.insertSelective(skThumbsUp);
			        try {				
				 		//MQ发布语音
				        NoticeEvent message = new NoticeEvent();
				        message.setType(NoticeEvent.type_thumbsUp);
				        message.setEventId(skThumbsUp.getId());
				        message.setTime(System.currentTimeMillis());
				        message.setVariety(NoticeEvent.variety_voice);
				 		contentRabbitmqSender.sendThumbsUpMessage(message);
				 		
				 		//消费者， 维护用户冗余基础数据 MQ语音
//				 		UserBaseDataMsg messageData = new UserBaseDataMsg();
//				 		messageData.setUserId(UserContextHolder.getUserIdByHeader());
//				 		messageData.setNum(1);
//				 		messageData.setCode(UserBaseTypeEnum.THUMBS_NUM.getCode());
//				 		contentRabbitmqSender.sendUserActionMessage(messageData);
				 		SkUserBaseInfo skUserBaseInfo = new SkUserBaseInfo();
				 		skUserBaseInfo.setUserId(UserContextHolder.getUserIdByHeader());
				 		skUserBaseInfo.setThumbsNum(1);
				 		skUserBaseInfoDAO.updateUserBaseContentNum(skUserBaseInfo);
			        }catch(Exception e) {
			        	
			        }
			}
		 if(1==param.getVoiceType()&&null!=skThumbsUpSelect) {
			    skThumbsUpDAO.deleteByPrimaryKey(skThumbsUpSelect.getId());
		 		//消费者， 维护用户冗余基础数据 MQ语音
//		 		UserBaseDataMsg messageData = new UserBaseDataMsg();
//		 		messageData.setUserId(UserContextHolder.getUserIdByHeader());
//		 		messageData.setNum(-1);
//		 		messageData.setCode(UserBaseTypeEnum.THUMBS_NUM.getCode());
//		 		contentRabbitmqSender.sendUserActionMessage(messageData);
		 		SkUserBaseInfo skUserBaseInfo = new SkUserBaseInfo();
		 		skUserBaseInfo.setUserId(UserContextHolder.getUserIdByHeader());
		 		skUserBaseInfo.setThumbsNum(-1);
		 		skUserBaseInfoDAO.updateUserBaseContentNum(skUserBaseInfo);
		 }
	        
		}
		return 0;
	}
   
	

}
