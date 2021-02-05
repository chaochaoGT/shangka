package com.geek.shengka.content.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.content.request.ThumbsUpRequest;
import com.geek.shengka.content.service.ThumbsUpService;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点赞
 * @author xuxuelei
 *
 */
@RestController
@IgnoreClientToken
@RequestMapping("/v1/app/thumsUp")
public class ThumbsUpController {

    @Autowired
    private ThumbsUpService thumbsUpService;
     
    /**
                   * 语音点赞
     * @param param
     * @return
     */
    @PostMapping("/thumbsUpVoice")
    public BaseResponse<Integer> thumbsUpVoice(@RequestBody @Valid ThumbsUpRequest param) {
        long userId = UserContextHolder.getUserIdByHeader();
        if (userId <= 0) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        return BaseResponse.newSuccess(thumbsUpService.thumbsUpVoice(param));
    }

    
   
 
 
}
