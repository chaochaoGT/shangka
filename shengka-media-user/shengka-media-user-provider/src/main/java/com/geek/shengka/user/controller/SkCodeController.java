package com.geek.shengka.user.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.user.service.SkCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 字典业务处理
 *
 * @author: yunfei
 * @create: 2019-08-01 11:14
 **/
@RestController
@RequestMapping("/v1/dict")
public class SkCodeController {

    @Autowired
    private SkCodeService skCodeService;

    /***
     * 关注/取消关注用户（粉丝）
     * @param codeType
     * @return
     */
    @GetMapping("/listConfig")
    @IgnoreClientToken
    public BaseResponse listConfig(String codeType) {
        if(StringUtils.isBlank(codeType)){
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        return BaseResponse.newSuccess(skCodeService.listConfig(codeType));
    }
}
