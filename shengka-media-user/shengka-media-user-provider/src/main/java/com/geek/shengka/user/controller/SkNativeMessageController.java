package com.geek.shengka.user.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.user.service.SkNativeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 系统消息
 *
 * @author: yunfei
 * @create: 2019-08-01 11:14
 **/
@RestController
@RequestMapping("/v1/native")
public class SkNativeMessageController {

    @Autowired
    private SkNativeMessageService noticMessageService;

    /***
     * 系统消息
     * @return
     */
    @GetMapping("/message")
    @IgnoreClientToken
    public BaseResponse message() {
        return noticMessageService.message();
    }
}
