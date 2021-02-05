package com.geek.shengka.user.controller;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.user.request.MyVoiceRequest;
import com.geek.shengka.user.service.SkUserVoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 我的发声业务
 *
 * @author: yunfei
 * @create: 2019-08-01 11:45
 **/
@RestController
@RequestMapping("/v1/voice")
public class SkUserVoiceController {

    @Autowired
    private SkUserVoiceService skUserVoiceService;

    /***
     * 我的发声
     * @param params
     * @return
     */
    @PostMapping("/myVoiceList")
    public BaseResponse myVoiceList(@Valid @RequestBody MyVoiceRequest params) {
        params.setUserId(UserContextHolder.getUserId());
        if(params.getUserId()<=0){
           return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        return BaseResponse.newSuccess(skUserVoiceService.myVoiceList(params));
    }
}
