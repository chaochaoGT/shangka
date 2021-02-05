package com.geek.shengka.gold.service;

import com.geek.shengka.common.basemodel.BaseResponse;


/**
 * 每日任务配置
 *
 * @author: yunfei
 * @create: 2019-08-02 15:06
 **/
public interface SkTaskConfigService {


    /**
     * 个人中心任务列表
     *
     * @param userId
     * @return
     */
    BaseResponse dailyConfig(Long userId);

    BaseResponse globalConfig();
}
