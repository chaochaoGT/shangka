package com.geek.shengka.user.service.impl;

import java.util.Date;

import com.geek.shengka.common.mq.NoticeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.geek.shengka.user.entity.SkCommentNotice;
import com.geek.shengka.user.entity.SkPublishVideo;
import com.geek.shengka.user.entity.vo.SkUserBaseInfoVO;
import com.geek.shengka.user.entity.vo.SkVoiceVideoInfo;
import com.geek.shengka.user.mapper.SkCommentNoticeDAO;
import com.geek.shengka.user.mapper.SkPublishVideoDAO;
import com.geek.shengka.user.mapper.SkUserBaseInfoDAO;
import com.geek.shengka.user.mapper.SkVoiceDAO;
import com.geek.shengka.user.rabbitmq.entity.CommentAndAtNoticeEvent;


@Service
public class NoticeServiceImpl {
	
	@Autowired
	private SkThumbsUpNoticeServiceImpl skThumbsUpNoticeServiceImpl;
	
    @Autowired
    private SkVoiceDAO skVoiceDAO;
	
    @Autowired
    private SkPublishVideoDAO skPublishVideoDAO;
    
    @Autowired
    private SkUserInfoServiceImpl skUserInfoServiceImpl;
    
    @Autowired
    private SkCommentNoticeDAO skCommentNoticeDAO;
    
    @Autowired
    private SkFansNoticeServiceImpl skFansNoticeServiceImpl;
	
	public void noticeProcess(NoticeEvent noticeEvent) {
		if(NoticeEvent.type_thumbsUp.equals(noticeEvent.getType())) {
			skThumbsUpNoticeServiceImpl.insertNotice(noticeEvent.getEventId(),noticeEvent.getVariety());
		}else if(NoticeEvent.type_attenttion.equals(noticeEvent.getType())) {
			skFansNoticeServiceImpl.insertNotice(noticeEvent.getEventId());
		}
	}
	
	public void commentNoticeProcess(CommentAndAtNoticeEvent commentAndAtEvent) {
    	Assert.notNull(commentAndAtEvent.getMediaId(), "视频id或语音id不能为空");
     	Assert.notNull(commentAndAtEvent.getUserId(), "用户id不能为空");
    	if(CommentAndAtNoticeEvent.TYPE_PUBLISHVIDEOAT.equals(commentAndAtEvent.getType())) {
    		
    		SkPublishVideo skPublishVideo = skPublishVideoDAO.selectByPrimaryKey(commentAndAtEvent.getMediaId());
    		if(skPublishVideo!=null) {
    			SkUserBaseInfoVO skVideoPublisher=skUserInfoServiceImpl.baseInfo(skPublishVideo.getPublishUserId());
    			SkUserBaseInfoVO beAtUser=skUserInfoServiceImpl.baseInfo(commentAndAtEvent.getUserId());
    			SkCommentNotice skCommentNotice=new SkCommentNotice();
    			skCommentNotice.setCreateTime(new Date());
    			skCommentNotice.setNickName(skVideoPublisher.getNickName());
    			skCommentNotice.setNoticeContent("发视频时@了你");
    			skCommentNotice.setNoticeState(SkCommentNotice.noticeState_notLook);
    			skCommentNotice.setNoticeType(SkCommentNotice.noticeType_publishVideoAt);
    			skCommentNotice.setNoticeUserId(beAtUser.getUserId());
    			skCommentNotice.setUpdateTime(skCommentNotice.getCreateTime());
    			skCommentNotice.setUserIcon(skVideoPublisher.getUserIcon());
    			skCommentNotice.setUserId(skVideoPublisher.getUserId());
    			skCommentNotice.setVideoId(skPublishVideo.getId());
    			skCommentNotice.setVideoUrl(skPublishVideo.getCoverImageUrl());
    			skCommentNoticeDAO.insertSelective(skCommentNotice);
    		}
    		
    	}else if(CommentAndAtNoticeEvent.TYPE_PUBLISHVOICEAT.equals(commentAndAtEvent.getType())) {
    		SkVoiceVideoInfo skVoice = skVoiceDAO.selectVoiceVideoByVoiceId(commentAndAtEvent.getMediaId());
    		if(skVoice!=null) {
    			SkUserBaseInfoVO skVoicePublisher=skUserInfoServiceImpl.baseInfo(skVoice.getUserId());
    			SkUserBaseInfoVO beAtUser=skUserInfoServiceImpl.baseInfo(commentAndAtEvent.getUserId());
    			SkCommentNotice skCommentNotice=new SkCommentNotice();
    			skCommentNotice.setCreateTime(new Date());
    			skCommentNotice.setNickName(skVoicePublisher.getNickName());
    			skCommentNotice.setNoticeContent("发语音评论时@了你");
    			skCommentNotice.setNoticeState(SkCommentNotice.noticeState_notLook);
    			skCommentNotice.setNoticeType(SkCommentNotice.noticeType_publishVoiceAt);
    			skCommentNotice.setNoticeUserId(beAtUser.getUserId());
    			skCommentNotice.setUpdateTime(skCommentNotice.getCreateTime());
    			skCommentNotice.setUserIcon(skVoicePublisher.getUserIcon());
    			skCommentNotice.setUserId(skVoicePublisher.getUserId());
    			skCommentNotice.setVideoId(skVoice.getVideoId());
    			skCommentNotice.setVideoUrl(skVoice.getVideoCoverImageUrl());
    			skCommentNotice.setVoiceId(skVoice.getId());
    			skCommentNotice.setVoiceUrl(skVoice.getVoiceUrl());
    			skCommentNotice.setVoiceDuration(skVoice.getDuration());
    			skCommentNoticeDAO.insertSelective(skCommentNotice);
    		}
    		
    		
    	}else if(CommentAndAtNoticeEvent.TYPE_PUBLISHVOICECOMMENT.equals(commentAndAtEvent.getType())) {
    		SkVoiceVideoInfo skVoice = skVoiceDAO.selectVoiceVideoByVoiceId(commentAndAtEvent.getMediaId());
    		if(skVoice!=null) {
    			SkUserBaseInfoVO skUserBaseInfoVO=skUserInfoServiceImpl.baseInfo(commentAndAtEvent.getUserId());
    			SkCommentNotice skCommentNotice=new SkCommentNotice();
    			skCommentNotice.setCreateTime(new Date());
    			skCommentNotice.setNickName(skUserBaseInfoVO.getNickName());
    			skCommentNotice.setNoticeContent("评论了你的视频");
    			skCommentNotice.setNoticeState(SkCommentNotice.noticeState_notLook);
    			skCommentNotice.setNoticeType(SkCommentNotice.noticeType_publishVoiceComment);
    			skCommentNotice.setNoticeUserId(skVoice.getVideoPublishUserId());
    			skCommentNotice.setUpdateTime(skCommentNotice.getCreateTime());
    			skCommentNotice.setUserIcon(skUserBaseInfoVO.getUserIcon());
    			skCommentNotice.setUserId(skUserBaseInfoVO.getUserId());
    			skCommentNotice.setVideoId(skVoice.getVideoId());
    			skCommentNotice.setVideoUrl(skVoice.getVideoCoverImageUrl());
    			skCommentNotice.setVoiceId(skVoice.getId());
    			skCommentNotice.setVoiceUrl(skVoice.getVoiceUrl());
      			skCommentNotice.setVoiceDuration(skVoice.getDuration());
    			skCommentNoticeDAO.insertSelective(skCommentNotice);
    		}
    	}
	}
}
