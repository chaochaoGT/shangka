package com.geek.shengka.content.controller;

import com.geek.shengka.common.annotation.OnlyUserIgnoreToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.content.request.*;
import com.geek.shengka.content.service.UserVideoBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 不感兴趣业务
 *
 * @author: yunfei
 * @create: 2019-07-31 17:06
 **/
@RestController
@RequestMapping("/v1/video")
public class UserVideoBehaviorController {

    @Autowired
    private UserVideoBehaviorService userVideoBehaviorService;

    /***
     * 视频不感兴趣
     * @param params
     * @return
     */
    @PostMapping("/disInterest")
    public BaseResponse videoDisInterest(@Valid @RequestBody NotInterestRequest params) {
        params.setUserId(UserContextHolder.getUserId());
        if (params.getUserId() <= 0) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        return userVideoBehaviorService.videoDisInterest(params);
    }

    /***
     * 视频举报
     * @param params
     * @return
     */
    @PostMapping("/report")
    public BaseResponse videoReport(@Valid @RequestBody VideoReportRequest params) {
        params.setUserId(UserContextHolder.getUserId());
        if (params.getUserId() <= 0) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        return userVideoBehaviorService.videoReport(params);
    }

    /***
     * 视频喜欢/不喜欢
     * @param params
     * @return
     */
    @PostMapping("/likeOrNot")
    public BaseResponse likeOrNot(@Valid @RequestBody LikeOrNotRequest params) {
        params.setUserId(UserContextHolder.getUserId());
        if (params.getUserId() <= 0) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        return userVideoBehaviorService.likeOrNot(params);
    }

    /***
     * 视频上报（新增用户观看历史）
     * @param params
     * @return
     */
    @PostMapping("/addWatchHistory")
    @OnlyUserIgnoreToken
    public BaseResponse addWatchHistory(@Valid @RequestBody AddWatchHistoryRequest params) {
        params.setUserId(UserContextHolder.getUserId());
        return userVideoBehaviorService.addWatchHistory(params);
    }

    /***
     * 用户观看历史(最近)
     * @param params
     * @return
     */
    @PostMapping("/userWatchHistory")
    public BaseResponse userWatchHistory(@Valid @RequestBody WatchHistoryRequest params) {
        params.setUserId(UserContextHolder.getUserId());
        if (params.getUserId() <= 0) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        return userVideoBehaviorService.userWatchHistory(params);
    }

    /***
     * 删除用户观看历史(最近)
     * @param params
     * @return
     */
    @PostMapping("/delUserWatchHistory")
    public BaseResponse delUserWatchHistory(@Valid @RequestBody DelWatchHistoryRequest params) {
        params.setUserId(UserContextHolder.getUserId());
        if (params.getUserId() <= 0) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        return userVideoBehaviorService.delUserWatchHistory(params);
    }
}
