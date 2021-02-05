package com.geek.shengka.user.service.impl;

import com.geek.shengka.common.util.CdnUrlUtils;
import com.geek.shengka.user.entity.SkPublishVideo;
import com.geek.shengka.user.entity.SkVoice;
import com.geek.shengka.user.mapper.SkPublishVideoDAO;
import com.geek.shengka.user.mapper.SkVoiceDAO;
import com.geek.shengka.user.request.MyVoiceRequest;
import com.geek.shengka.user.response.MyVoiceResponse;
import com.geek.shengka.user.service.SkUserVoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 我的发声业务实现
 *
 * @author: yunfei
 * @create: 2019-08-01 11:49
 **/
@Service
public class SkUserVoiceServiceImpl implements SkUserVoiceService {


    @Autowired
    private SkVoiceDAO skVoiceDAO;

    @Autowired
    private SkPublishVideoDAO skPublishVideoDAO;


    @Override
    public List<MyVoiceResponse> myVoiceList(MyVoiceRequest params) {
        Integer start = (params.getPageNum() - 1) * params.getPageSize();
        Integer size = params.getPageSize();
        List<SkVoice> list = skVoiceDAO.myVoiceList(params.getUserId(), start, size);
        return list.stream().map(skVoice -> translation(skVoice)).collect(Collectors.toList());
    }

    /***
     * 转换
     * @param skVoice
     * @return
     */
    private final MyVoiceResponse translation(SkVoice skVoice) {
        MyVoiceResponse myVoiceResponse = new MyVoiceResponse();
        myVoiceResponse.setCreatTime(skVoice.getCreateTime());
        myVoiceResponse.setVoiceUrl(CdnUrlUtils.transferCdn(skVoice.getVoiceUrl()));
        myVoiceResponse.setVideoId(skVoice.getVideoId());
        SkPublishVideo video = skPublishVideoDAO.selectByPrimaryKey(skVoice.getVideoId());
        if(video!=null){
            myVoiceResponse.setCoverImage(video.getCoverImageUrl());
            myVoiceResponse.setTitle(video.getTitle());
            myVoiceResponse.setCategoryId(video.getConCategoryId());
        }
        myVoiceResponse.setDuration(skVoice.getDuration());
        myVoiceResponse.setVoiceId(skVoice.getId());
        return myVoiceResponse;
    }
}
