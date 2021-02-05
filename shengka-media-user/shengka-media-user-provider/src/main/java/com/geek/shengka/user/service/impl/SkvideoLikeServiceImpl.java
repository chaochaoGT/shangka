package com.geek.shengka.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.common.basemodel.BaseMediaInfo;
import com.geek.shengka.common.basemodel.PageRequest;
import com.geek.shengka.common.proxy.ContentVideoInfoProxy;
import com.geek.shengka.common.request.BaseContentRequest;
import com.geek.shengka.common.util.CdnUrlUtils;
import com.geek.shengka.user.constant.UserConstant;
import com.geek.shengka.user.entity.vo.SkPublishVideoVO;
import com.geek.shengka.user.mapper.SkPublishVideoDAO;
import com.geek.shengka.user.mapper.SkVideoLikeDAO;
import com.geek.shengka.user.request.UserLikePushRequest;
import com.geek.shengka.user.service.SkvideoLikeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Filename: SkvideoLikeServiceImpl
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/8 ;
 */
@Service
@Slf4j
public class SkvideoLikeServiceImpl implements SkvideoLikeService {
    private Logger logger = LoggerFactory.getLogger(SkvideoLikeServiceImpl.class);
    @Autowired
    private SkVideoLikeDAO skVideoLikeDAO;

    @Autowired
    private SkPublishVideoDAO skPublishVideoDAO;
    @Autowired
    private ContentVideoInfoProxy contentVideoInfoProxy;


    @Override
    public List<BaseMediaInfo> myVideoLikeList(Long userId, PageRequest params) {
        List<String> vids = skVideoLikeDAO.myVideoLikeList(userId, params.getStartRecordNumb(), params.getPageCount());
        try {
            if (CollectionUtils.isEmpty(vids)){
                return new ArrayList<>();
            }
            BaseContentRequest request = BaseContentRequest.builder().videoIds(vids).pageNo(UserConstant.default_pageIndex).pageSize(vids.size()).build();
            List<BaseMediaInfo> manyMedias = contentVideoInfoProxy.multiConditionGetMedias(request);
            if (CollectionUtils.isEmpty(manyMedias)) {
                logger.info("请求内容中心 返回视频为空 multiConditionGetMedias  request={} skvids.size={}", JSON.toJSONString(request), vids.size());
                return new ArrayList<>(1);
            }
            Map<String, BaseMediaInfo> collect = manyMedias.stream().collect(Collectors.toMap(BaseMediaInfo::getId,
                    bm -> bm));
            return vids.stream().filter(id -> null != collect.get(id)).map(v -> {
                BaseMediaInfo mediaInfo = collect.get(v);
                mediaInfo.setUrl(CdnUrlUtils.transferCdn(mediaInfo.getUrl()));
                mediaInfo.setCoverImage(CdnUrlUtils.transferCdn(mediaInfo.getCoverImage()));
                return mediaInfo;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("myVideoLikeList is error params={} e={}", userId, e);
        }
        return new ArrayList<>(1);
    }

    @Override
    public List<SkPublishVideoVO> publishMediaList(Long userId, PageRequest params) {
        log.info("查看自己发布视频的接口参数：userId={},params={}", userId, JSONObject.toJSONString(params));
        Map<String, Object> paraMap = new HashMap<>(5);
        paraMap.put("userId", userId);
        paraMap.put("startRecordNumb", params.getStartRecordNumb());
        paraMap.put("pageCount", params.getPageCount());

        //所有视频
        List<SkPublishVideoVO> vos = skPublishVideoDAO.publishMediaListByUserId(paraMap);
        if (CollectionUtils.isEmpty(vos)) {
            return new ArrayList<>();
        }
        //处理有效视频
        List<String> vids =
                vos.stream().filter(vo -> UserConstant.push_video_enble_1 == vo.getEnable()).map(SkPublishVideoVO::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(vids)) {
            List<BaseMediaInfo> manyMedias =
                    contentVideoInfoProxy.multiConditionGetMedias(BaseContentRequest.builder().userId(userId).videoIds(vids).pageNo(UserConstant.default_pageIndex).pageSize(vids.size()).build());
            if (!CollectionUtils.isEmpty(manyMedias)) {
                Map<String, BaseMediaInfo> collect =
                        manyMedias.stream().collect(Collectors.toMap(BaseMediaInfo::getId, bm -> bm));
                vos.forEach(vo -> {
                    if (null != collect.get(vo.getId())) {
                        BaseMediaInfo mediaInfo = collect.get(vo.getId());
                        vo.setGiveThumbsNums(mediaInfo.getGiveThumbsNums());
                        vo.setCommentNums(mediaInfo.getCommentNums());
                        vo.setDuration(mediaInfo.getDuration());
                        vo.setWatchedTimes(mediaInfo.getWatchedTimes());
                        vo.setUrl(CdnUrlUtils.transferCdn(mediaInfo.getUrl()));
                        vo.setCoverImage(CdnUrlUtils.transferCdn(mediaInfo.getCoverImage()));
                    } else {
                        vo.setUrl(CdnUrlUtils.transferCdn(vo.getUrl()));
                        vo.setCoverImage(CdnUrlUtils.transferCdn(vo.getCoverImage()));
                    }
                });
            }
        } else {
            vos.forEach(vo -> {
                vo.setUrl(CdnUrlUtils.transferCdn(vo.getUrl()));
                vo.setCoverImage(CdnUrlUtils.transferCdn(vo.getCoverImage()));
            });
        }
        return vos;
    }

    @Override
    public List<SkPublishVideoVO> otherPublishMediaList(Long userId, UserLikePushRequest params) {
        Map<String, Object> paraMap = new HashMap<>(5);
        paraMap.put("userId", userId);
        paraMap.put("startRecordNumb", Objects.isNull(params.getStartRecordNumb()) ? 0 : params.getStartRecordNumb());
        paraMap.put("pageCount", Objects.isNull(params.getPageCount()) ? 10 : params.getPageCount());
        paraMap.put("enable", UserConstant.push_video_enble_1);
        //有效视频
        List<SkPublishVideoVO> vos = skPublishVideoDAO.publishMediaListByUserId(paraMap);
        if (CollectionUtils.isEmpty(vos)) {
            return new ArrayList<>(1);
        }
        //有效视频ids
        List<String> vids =
                vos.stream().filter(vo -> UserConstant.push_video_enble_1 == vo.getEnable()).map(SkPublishVideoVO::getId).collect(Collectors.toList());

        BaseContentRequest request = BaseContentRequest.builder().userId(userId).videoIds(vids).pageNo(UserConstant.default_pageIndex).pageSize(vids.size()).build();

        List<BaseMediaInfo> manyMedias = contentVideoInfoProxy.multiConditionGetMedias(request);
        if (!CollectionUtils.isEmpty(manyMedias)) {
            Map<String, BaseMediaInfo> collect = manyMedias.stream().collect(Collectors.toMap(BaseMediaInfo::getId,
                    bm -> bm));
            vos.forEach(vo -> {
                if (null != collect.get(vo.getId())) {
                    BaseMediaInfo mediaInfo = collect.get(vo.getId());
                    vo.setGiveThumbsNums(mediaInfo.getGiveThumbsNums());
                    vo.setCommentNums(mediaInfo.getCommentNums());
                    vo.setDuration(mediaInfo.getDuration());
                    vo.setWatchedTimes(mediaInfo.getWatchedTimes());
                    vo.setUrl(CdnUrlUtils.transferCdn(mediaInfo.getUrl()));
                    vo.setCoverImage(CdnUrlUtils.transferCdn(mediaInfo.getCoverImage()));
                }else {
                    vo.setUrl(CdnUrlUtils.transferCdn(vo.getUrl()));
                    vo.setCoverImage(CdnUrlUtils.transferCdn(vo.getCoverImage()));
                }
            });
        }else {
            vos.forEach(vo -> {
                vo.setUrl(CdnUrlUtils.transferCdn(vo.getUrl()));
                vo.setCoverImage(CdnUrlUtils.transferCdn(vo.getCoverImage()));
            });
        }
        return vos.stream().filter(vo -> UserConstant.push_video_enble_1 == vo.getEnable()).collect(Collectors.toList());
    }
}
