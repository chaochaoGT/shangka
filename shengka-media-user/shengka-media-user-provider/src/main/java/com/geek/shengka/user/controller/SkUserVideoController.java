package com.geek.shengka.user.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.annotation.OnlyUserIgnoreToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseBeanCode;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.user.request.UserLikePushRequest;
import com.geek.shengka.user.service.SkvideoLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 我的作品相关
 *
 * @author: yunfei
 * @create: 2019-08-01 11:45
 **/
@RestController
@RequestMapping("/v1/app")
public class SkUserVideoController {

    @Autowired
    private SkvideoLikeService skvideoLikeService;

    /***
     * 用户喜欢视频列表
     * @param params
     * @return
     */
    @PostMapping("/videoLikeList")
    public BaseResponse myVideoLikeList(@RequestBody UserLikePushRequest params) {
        Long userId = UserContextHolder.getUserId();
        return BaseResponse.newSuccess(skvideoLikeService.myVideoLikeList(userId,params));
    }

    /***
     * 别人的喜欢列表
     * @param params
     * @return
     */
    @PostMapping("/otherVideoLikeList")
    @IgnoreClientToken
    public BaseResponse otherVideoLikeList(@RequestBody UserLikePushRequest params) {
        if (null==params.getUserId()||params.getUserId()<=0){
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(), ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        return BaseResponse.newSuccess(skvideoLikeService.myVideoLikeList(params.getUserId(),params));
    }

    /***
     * 用户发布视频列表
     * @param params
     * @return
     */
    @PostMapping("/publishMediaList")
    public BaseResponse publishMediaList(@RequestBody UserLikePushRequest params) {
        Long userId = UserContextHolder.getUserId();
        return BaseResponse.newSuccess(skvideoLikeService.publishMediaList(userId,params));
    }

    /***
     * 别人发布的视频列表
     * @param params
     * @return
     */
    @PostMapping("/otherPublishMediaList")
    @IgnoreClientToken
    public BaseResponse otherPublishMediaList(@RequestBody UserLikePushRequest params) {
        if (null==params.getUserId()||params.getUserId()<=0){
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(), ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        return BaseResponse.newSuccess(skvideoLikeService.otherPublishMediaList(params.getUserId(),params));
    }
}
