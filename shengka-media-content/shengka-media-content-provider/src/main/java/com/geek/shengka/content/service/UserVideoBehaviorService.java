package com.geek.shengka.content.service;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.content.request.*;

/**
 * 用户行为业务（举报，不感兴趣，喜欢/不喜欢，观看记录等）
 *
 * @author: yunfei
 * @create: 2019-07-31 17:17
 **/
public interface UserVideoBehaviorService {

    /**
     * 视频不感兴趣
     *
     * @param params
     * @return
     */
    BaseResponse videoDisInterest(NotInterestRequest params);

    /**
     * 视频举报
     *
     * @param params
     * @return
     */
    BaseResponse videoReport(VideoReportRequest params);

    /**
     * 视频喜欢/不喜欢
     *
     * @param params
     * @return
     */
    BaseResponse likeOrNot(LikeOrNotRequest params);

    /**
     * 记录视频观看历史（视频上报）
     *
     * @param params
     * @return
     */
    BaseResponse addWatchHistory(AddWatchHistoryRequest params);

    /**
     * 用户观看历史(最近)
     *
     * @param params
     * @return
     */
    BaseResponse userWatchHistory(WatchHistoryRequest params);

    /**
     * 删除用户观看历史(最近)
     *
     * @param params
     * @return
     */
    BaseResponse delUserWatchHistory(DelWatchHistoryRequest params);
}
