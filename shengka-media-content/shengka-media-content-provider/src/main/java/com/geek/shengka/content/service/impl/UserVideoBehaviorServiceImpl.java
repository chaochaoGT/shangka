package com.geek.shengka.content.service.impl;

import com.geek.shengka.common.basemodel.BaseMediaInfo;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.common.enums.ReportTypeEnum;
import com.geek.shengka.common.enums.UserActionEventEnum;
import com.geek.shengka.common.enums.UserBaseTypeEnum;
import com.geek.shengka.common.http.ContextTools;
import com.geek.shengka.common.mq.NoticeEvent;
import com.geek.shengka.common.mq.UserBaseDataMsg;
import com.geek.shengka.common.proxy.ContentVideoInfoProxy;
import com.geek.shengka.common.request.BaseContentRequest;
import com.geek.shengka.common.request.ContextReportRequest;
import com.geek.shengka.content.entity.SkNotInterest;
import com.geek.shengka.content.entity.SkReport;
import com.geek.shengka.content.entity.SkVideoLike;
import com.geek.shengka.content.entity.SkWatchHistory;
import com.geek.shengka.content.enums.VideoLikeOrNotEnum;
import com.geek.shengka.content.mapper.SkNotInterestDAO;
import com.geek.shengka.content.mapper.SkReportDAO;
import com.geek.shengka.content.mapper.SkVideoLikeDAO;
import com.geek.shengka.content.mapper.SkWatchHistoryDAO;
import com.geek.shengka.content.rabbitmq.RabbitmqSender;
import com.geek.shengka.content.request.*;
import com.geek.shengka.content.response.WatchHistoryResponse;
import com.geek.shengka.content.service.UserVideoBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户行为业务（举报，不感兴趣，喜欢/不喜欢，观看记录等）实现
 *
 * @author: yunfei
 * @create: 2019-07-31 17:19
 **/
@Service
public class UserVideoBehaviorServiceImpl implements UserVideoBehaviorService {


    @Autowired
    private SkReportDAO skReportDAO;

    @Autowired
    private SkNotInterestDAO skNotInterestDAO;

    @Autowired
    private SkVideoLikeDAO skVideoLikeDAO;

    @Autowired
    private SkWatchHistoryDAO skWatchHistoryDAO;

    @Autowired
    private ContentVideoInfoProxy contentVideoInfoProxy;

    @Autowired
    private RabbitmqSender rabbitmqSender;


    @Override
    public BaseResponse videoDisInterest(NotInterestRequest params) {
        SkNotInterest skNotInterest = skNotInterestDAO.selectByUidAndVideoId(params.getUserId(), params.getVideoId());
        if (skNotInterest == null) {
            skNotInterest = new SkNotInterest();
            skNotInterest.setUserId(params.getUserId());
            skNotInterest.setVideoId(params.getVideoId());
            skNotInterest.setReason(params.getReason());
            skNotInterestDAO.insertSelective(skNotInterest);
            //TODO 上报内容中心(内容中心无接口提供)
        }

        return BaseResponse.newSuccess();
    }

    @Override
    public BaseResponse videoReport(VideoReportRequest params) {
        SkReport skReport = skReportDAO.selectByUidAndVideoId(params.getUserId(), params.getVideoId());
        if (skReport != null) {
            return BaseResponse.newFailure(ResponseCode.NOT_REPART_REPORT_BEUID);
        }
        skReport = new SkReport();
        skReport.setUserId(params.getUserId());
        skReport.setVideoId(params.getVideoId());
        skReport.setReportContent(params.getReason());
        skReport.setReportType((byte) ReportTypeEnum.video.getCode());
        skReportDAO.insertSelective(skReport);
        // 上报内容中心
        reportUserEvent(skReport.getUserId(), skReport.getVideoId(), skReport.getReportContent(), UserActionEventEnum.report.getType());
        return BaseResponse.newSuccess();
    }

    @Override
    public BaseResponse likeOrNot(LikeOrNotRequest params) {
        SkVideoLike skVideoLike = skVideoLikeDAO.selectByUidAndVideoId(params.getUserId(), params.getVideoId());
        if (VideoLikeOrNotEnum.LIKE.getCode().equals(params.getLikeState()) && skVideoLike == null) {
            skVideoLike = new SkVideoLike();
            skVideoLike.setUserId(params.getUserId());
            skVideoLike.setVideoId(params.getVideoId());
            skVideoLike.setRemark(params.getReason());
            skVideoLikeDAO.insertSelective(skVideoLike);
            // 更新用户喜欢维度
            sendUserBaseDataMsg(params.getUserId(), 1, UserBaseTypeEnum.LIKE_WORKS_NUM.getCode());
            reportUserEvent(skVideoLike.getUserId(), skVideoLike.getVideoId(), skVideoLike.getRemark(), UserActionEventEnum.like.getType());
            NoticeEvent msg = new NoticeEvent();
            msg.setType(NoticeEvent.type_thumbsUp);
            msg.setEventId(skVideoLike.getId());
            msg.setTime(System.currentTimeMillis());
            msg.setVariety(NoticeEvent.variety_video);
            rabbitmqSender.sendCommentNoticeMsg(msg);
        } else if (VideoLikeOrNotEnum.NOT_LIKE.getCode().equals(params.getLikeState()) && skVideoLike != null) {
            skVideoLikeDAO.deleteByPrimaryKey(skVideoLike.getId());
            // 更新用户不喜欢维度
            sendUserBaseDataMsg(params.getUserId(), -1, UserBaseTypeEnum.LIKE_WORKS_NUM.getCode());
            reportUserEvent(skVideoLike.getUserId(), skVideoLike.getVideoId(), skVideoLike.getRemark(), UserActionEventEnum.dislike.getType());
        }
        return BaseResponse.newSuccess();
    }

    @Override
    public BaseResponse addWatchHistory(AddWatchHistoryRequest params) {
        // 观看次数同步内容内容中心，点次加1
        reportUserEvent(params.getUserId(), params.getVideoId(), "", UserActionEventEnum.play_start.getType());
        if (params.getUserId() < 0) {
            return BaseResponse.newSuccess();
        }
        SkWatchHistory skWatchHistory = skWatchHistoryDAO.selectByUidAndVideoId(params.getUserId(), params.getVideoId());
        if (skWatchHistory == null) {
            skWatchHistory = new SkWatchHistory();
            skWatchHistory.setUserId(params.getUserId());
            skWatchHistory.setVideoId(params.getVideoId());
            skWatchHistoryDAO.insertSelective(skWatchHistory);
            // 更新用户观看维度
            sendUserBaseDataMsg(params.getUserId(), 1, UserBaseTypeEnum.NEAREST_NUM.getCode());
        } else {
            skWatchHistory.setCreateTime(new Date());
            skWatchHistoryDAO.updateByPrimaryKeySelective(skWatchHistory);
        }
        return BaseResponse.newSuccess();
    }

    @Override
    public BaseResponse userWatchHistory(WatchHistoryRequest params) {
        Integer start = (params.getPageNum() - 1) * params.getPageSize();
        Integer size = params.getPageSize();
        List<SkWatchHistory> list = skWatchHistoryDAO.selectUserWatchHistory(params.getUserId(), start, size);
        if (CollectionUtils.isEmpty(list)) {
            return BaseResponse.newSuccess();
        }
        List<String> videos = list.stream().map(SkWatchHistory::getVideoId).collect(Collectors.toList());
        List<BaseMediaInfo> medias = contentVideoInfoProxy.multiConditionGetMedias(BaseContentRequest.builder().videoIds(videos).pageNo(1).pageSize(videos.size()).build());
        if (CollectionUtils.isEmpty(medias)) {
            return BaseResponse.newSuccess();
        }
        List<WatchHistoryResponse> watchList = videos.stream().map(videoId -> translation(videoId, medias)).collect(Collectors.toList());
        watchList.removeAll(Collections.singleton(null));
        return BaseResponse.newSuccess(watchList);
    }

    private WatchHistoryResponse translation(String videoId, List<BaseMediaInfo> medias) {
        BaseMediaInfo baseMediaInfo = medias.stream().filter(baseInfo -> videoId.equals(baseInfo.getId())).findFirst().orElse(null);
        if (baseMediaInfo != null) {
            WatchHistoryResponse historyResponse = new WatchHistoryResponse();
            historyResponse.setVedioId(baseMediaInfo.getId());
            historyResponse.setVedioUrl(baseMediaInfo.getUrl());
            historyResponse.setCoverImage(baseMediaInfo.getCoverImage());
            historyResponse.setLikeNum(baseMediaInfo.getGiveThumbsNums());
            return historyResponse;
        }
        return null;
    }

    @Override
    public BaseResponse delUserWatchHistory(DelWatchHistoryRequest params) {
        int count = 0;
        if (params.getFlag() == 1) {
            count = skWatchHistoryDAO.delUserWatchHistory(params.getUserId(), null);
        } else {
            if (CollectionUtils.isEmpty(params.getVideoId())) {
                return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
            }
            count = skWatchHistoryDAO.delUserWatchHistory(params.getUserId(), params.getVideoId());
        }
        count = 0 - count;
        // 更新用户观看维度
        sendUserBaseDataMsg(params.getUserId(), count, UserBaseTypeEnum.NEAREST_NUM.getCode());
        return BaseResponse.newSuccess();
    }

    /**
     * 上报用户点击内容事件
     *
     * @param userId
     * @param videoId
     * @param remark
     * @param type
     */
    private final void reportUserEvent(Long userId, String videoId, String remark, int type) {
        ContextReportRequest request = new ContextReportRequest();
        request.setType(type);
        request.setRemark(remark);
        request.setVideoId(videoId);
        request.setUserId(userId);
        request.setVersion(ContextTools.getVersion());
        rabbitmqSender.sendContentReportMessage(request);
    }

    /***
     * 发送维护用户冗余数据mq消息
     * @param userId
     * @param num
     * @param code
     */
    private final void sendUserBaseDataMsg(Long userId, Integer num, int code) {
        UserBaseDataMsg msg = new UserBaseDataMsg();
        msg.setNum(num);
        msg.setUserId(userId);
        msg.setCode(code);
        rabbitmqSender.sendUserDataMessage(msg);
    }
}
