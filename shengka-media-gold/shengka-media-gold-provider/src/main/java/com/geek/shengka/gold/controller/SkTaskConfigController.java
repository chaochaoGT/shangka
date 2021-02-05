package com.geek.shengka.gold.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.annotation.OnlyUserIgnoreToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.gold.service.SkTaskConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 每日任务业务
 *
 * @author: yunfei
 * @create: 2019-08-01 14:59
 **/
@RestController
@RequestMapping("/v1/task")
public class SkTaskConfigController {


    @Autowired
    private SkTaskConfigService skTaskConfigService;


    /**
     * 每日任务配置列表
     *
     * @return
     */
    @GetMapping("/dailyConfig")
    public BaseResponse dailyConfig() {
        Long userId = UserContextHolder.getUserId();
        return skTaskConfigService.dailyConfig(userId<0?null:userId);
    }


    /**
     * 全局金币配置
     *
     * @return
     */
    @GetMapping("/globalConfig")
    @IgnoreClientToken
    public BaseResponse globalConfig() {
        return skTaskConfigService.globalConfig();
    }

}
