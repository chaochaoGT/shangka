package com.geek.shengka.user.service;

import com.geek.shengka.user.request.MyVoiceRequest;
import com.geek.shengka.user.response.MyVoiceResponse;

import java.util.List;

/**
 * 我的发声业务
 * @author: yunfei
 * @create: 2019-08-01 11:48
 **/
public interface SkUserVoiceService {


    /***
     * 获取我的发声列表
     * @param params
     * @return
     */
    List<MyVoiceResponse> myVoiceList(MyVoiceRequest params);
}
