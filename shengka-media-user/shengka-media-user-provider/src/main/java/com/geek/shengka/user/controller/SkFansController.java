package com.geek.shengka.user.controller;

import com.geek.shengka.common.annotation.OnlyUserIgnoreToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseBeanCode;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.user.request.AttentionRequest;
import com.geek.shengka.user.request.FansRequest;
import com.geek.shengka.user.service.SkFansService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 粉丝业务处理
 *
 * @author: yunfei
 * @create: 2019-08-01 11:14
 **/
@RestController
@RequestMapping("/v1/fans")
public class SkFansController {

    @Autowired
    private SkFansService skFansService;

    /***
     * 关注/取消关注用户（粉丝）
     * @param params
     * @return
     */
    @PostMapping("/attentionOrNot")
    public BaseResponse attentionOrNot(@Valid @RequestBody AttentionRequest params) {
        params.setUserId(UserContextHolder.getUserId());
        return skFansService.attentionOrNot(params);
    }

    /***
     * 查看自己关注/（粉丝）列表
     * @param params
     * @return
     */
    @PostMapping("/attentionFansList")
    public BaseResponse attentionFansList(@RequestBody FansRequest params) {
        if (params.getUserLabel()>1) {
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(), ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        params.setUserId(UserContextHolder.getUserId());
        return BaseResponse.newSuccess(skFansService.attentionFansList(params));
    }

    /***
     * 查看别人关注/（粉丝）列表
     * @param params
     * @return
     */
    @PostMapping("/otterAttentionFansList")
    @OnlyUserIgnoreToken
    public BaseResponse otterAttentionFansList(@RequestBody FansRequest params) {
        //userId不为空标识别人的粉丝或关注
        if ((null == params.getUserId() || params.getUserId() <= 0 ||params.getUserLabel() > 1)) {
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(), ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        return BaseResponse.newSuccess(skFansService.otterAttentionFansList(params,UserContextHolder.getUserId()));
    }

    /**
     * 搜索自己关注的用户
     * @param params
     * @return
     */
    @PostMapping("/findFansList")
    public BaseResponse findFansList( @RequestBody FansRequest params) {
        if(StringUtils.isEmpty(params.getKeyWord())){
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(), ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        params.setUserId(UserContextHolder.getUserId());
        return BaseResponse.newSuccess(skFansService.findFansList(params));
    }
}
