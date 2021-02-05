package com.geek.shengka.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.context.BaseContextHandler;
import com.geek.shengka.common.mq.NoticeEvent;
import com.geek.shengka.common.util.CdnUrlUtils;
import com.geek.shengka.user.entity.SkPublishVideo;
import com.geek.shengka.user.entity.SkThumbsUp;
import com.geek.shengka.user.entity.SkThumbsUpNotice;
import com.geek.shengka.user.entity.SkVideoLike;
import com.geek.shengka.user.entity.vo.SkThumbsUpNoticeVO;
import com.geek.shengka.user.entity.vo.SkUserBaseInfoVO;
import com.geek.shengka.user.entity.vo.SkVoiceVideoInfo;
import com.geek.shengka.user.mapper.SkPublishVideoDAO;
import com.geek.shengka.user.mapper.SkThumbsUpNoticeDAO;
import com.geek.shengka.user.mapper.SkVideoLikeDAO;
import com.geek.shengka.user.mapper.SkVoiceDAO;
import com.geek.shengka.user.reponsecode.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * 用户互粉
 *
 * @author: yunfei
 * @create: 2019-08-01 11:26
 **/

@Service
public class SkThumbsUpNoticeServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkThumbsUpNoticeServiceImpl.class);

    @Autowired
    private SkThumbsUpNoticeDAO skThumbsUpNoticeDAO;

    @Autowired
    private SkThumbsUpServiceImpl skThumbsUpServiceImpl;

    @Autowired
    private SkUserInfoServiceImpl skUserInfoServiceImpl;

    @Autowired
    private SkVoiceDAO skVoiceDAO;

    @Autowired
    private SkPublishVideoDAO skPublishVideoDAO;

    @Autowired
    private SkVideoLikeDAO skVideoLikeDAO;

    // @Transactional(rollbackFor = Exception.class)
    public BaseResponse<List<SkThumbsUpNoticeVO>> noticeList(int pageIndex, int pageSize) {
        BaseResponse<List<SkThumbsUpNoticeVO>> response = BaseResponse.newSuccess(null);
        String userId = BaseContextHandler.getCurrentUId();
        if (StringUtils.isBlank(userId) || Long.valueOf(userId) <= 0L) {
            response = BaseResponse.newFailure(ResponseCode.USER_ERROR);
            return response;
        }
//        PageHelper.startPage(pageIndex, pageSize);
        if(pageIndex<=0){
            pageIndex=1;
        }
        List<SkThumbsUpNoticeVO> fansNoticelist = skThumbsUpNoticeDAO.findThumbsUpNoticeListByUserId(Long.valueOf(userId),(pageIndex-1)*pageSize, pageSize);
        fansNoticelist.forEach(f->{
            f.setVideoUrl(CdnUrlUtils.transferCdn(f.getVideoUrl()));
            f.setVoiceUrl(CdnUrlUtils.transferCdn(f.getVoiceUrl()));

        });
        response.setData(fansNoticelist);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertNotice(Long id, String type) {
        Assert.notNull(id, "id不能为空");
        if (NoticeEvent.variety_voice.equals(type)) {
            SkThumbsUp skThumbsUp = skThumbsUpServiceImpl.findById(id);
            if (skThumbsUp == null) {
                LOGGER.info("========没有发现点赞语音数据===========" + id);
                return;
            }
            SkUserBaseInfoVO skUserBaseInfoVO = skUserInfoServiceImpl.baseInfo(skThumbsUp.getUserId());
            SkThumbsUpNotice skThumbsUpNotice = new SkThumbsUpNotice();
            if (skUserBaseInfoVO != null) {
                skThumbsUpNotice.setNickName(skUserBaseInfoVO.getNickName());
                skThumbsUpNotice.setUserIcon(skUserBaseInfoVO.getUserIcon());
            }
            skThumbsUpNotice.setCreateTime(new Date());
            skThumbsUpNotice.setNoticeState(SkThumbsUpNotice.noticeState_notLook);
            skThumbsUpNotice.setThumbsId(skThumbsUp.getId());
            skThumbsUpNotice.setThumbType((byte) 2);
            skThumbsUpNotice.setUpdateTime(skThumbsUpNotice.getCreateTime());
            skThumbsUpNotice.setUserId(skThumbsUp.getUserId());
            skThumbsUpNotice.setNoticeContent("赞了你的语音");
            SkVoiceVideoInfo skVoice = skVoiceDAO.selectVoiceVideoByVoiceId(skThumbsUp.getMediaId());
            LOGGER.info("========被点赞语音信息===========" + JSON.toJSONString(skVoice));
            if (skVoice != null) {
                skThumbsUpNotice.setNoticeUserId(skVoice.getVideoPublishUserId());
                skThumbsUpNotice.setVideoId(skVoice.getVideoId());
                skThumbsUpNotice.setVideoUrl(skVoice.getVideoCoverImageUrl());
                skThumbsUpNotice.setVoiceId(skVoice.getId());
                skThumbsUpNotice.setVoiceUrl(skVoice.getVoiceUrl());
                skThumbsUpNotice.setVoiceDuration(skVoice.getDuration());
                skThumbsUpNoticeDAO.insertSelective(skThumbsUpNotice);
            }
        } else {
            SkVideoLike videoLike = skVideoLikeDAO.selectByPrimaryKey(id);
            if (videoLike == null) {
                LOGGER.info("========没有发现点赞语音数据===========" + id);
                return;
            }
            SkUserBaseInfoVO skUserBaseInfoVO = skUserInfoServiceImpl.baseInfo(videoLike.getUserId());
            SkThumbsUpNotice skThumbsUpNotice = new SkThumbsUpNotice();
            skThumbsUpNotice.setCreateTime(new Date());
            if (skUserBaseInfoVO != null) {
                skThumbsUpNotice.setNickName(skUserBaseInfoVO.getNickName());
                skThumbsUpNotice.setUserIcon(skUserBaseInfoVO.getUserIcon());
            }
            skThumbsUpNotice.setNoticeState(SkThumbsUpNotice.noticeState_notLook);
            skThumbsUpNotice.setThumbsId(videoLike.getId());
            skThumbsUpNotice.setThumbType((byte) 1);
            skThumbsUpNotice.setUpdateTime(skThumbsUpNotice.getCreateTime());
            skThumbsUpNotice.setUserId(videoLike.getUserId());
            skThumbsUpNotice.setNoticeContent("赞了你的作品");
            SkPublishVideo skPublishVideo = skPublishVideoDAO.selectByPrimaryKey(videoLike.getVideoId());
            LOGGER.info("========被点赞作品信息===========" + JSON.toJSONString(skPublishVideo));
            if (skPublishVideo != null) {
                skThumbsUpNotice.setVideoId(skPublishVideo.getId());
                skThumbsUpNotice.setVideoUrl(skPublishVideo.getCoverImageUrl());
                skThumbsUpNotice.setNoticeUserId(skPublishVideo.getPublishUserId());
                skThumbsUpNoticeDAO.insertSelective(skThumbsUpNotice);
            }
        }
    }

}
