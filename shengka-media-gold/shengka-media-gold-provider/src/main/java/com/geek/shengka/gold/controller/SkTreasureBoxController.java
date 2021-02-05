package com.geek.shengka.gold.controller;

import com.geek.shengka.common.annotation.OnlyUserIgnoreToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseCode;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.gold.api.ITreasureBoxApi;
import com.geek.shengka.gold.request.OpenBoxRequest;
import com.geek.shengka.gold.request.TreasureBoxRequest;
import com.geek.shengka.gold.service.SkTreasureBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 宝箱业务
 *
 * @author: yunfei
 * @create: 2019-08-01 14:59
 **/
@RestController
@RequestMapping("/v1/treasureBox")
public class SkTreasureBoxController implements ITreasureBoxApi {


    @Autowired
    private SkTreasureBoxService skTreasureBoxService;


    /**
     * 宝箱配置 返回剩余次数
     *
     * @return
     */
    @Override
    @GetMapping("/config")
    @OnlyUserIgnoreToken
    public BaseResponse config() {
        Long userId = UserContextHolder.getUserId();
        return BaseResponse.newSuccess(skTreasureBoxService.config(userId));
    }

    /**
     * 开启宝箱
     * @return
     */
    @GetMapping("/open")
    public BaseResponse open(OpenBoxRequest params) {
        params.setUserId(UserContextHolder.getUserId());
        if(params.getUserId()<=0||params.getConfigId()==null){
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        return skTreasureBoxService.open(params);
    }

    /**
     * 领取宝箱
     * @return
     */
    @PostMapping("/getCoin")
    public BaseResponse getCoin(@Valid @RequestBody TreasureBoxRequest params) {
        params.setUserId(UserContextHolder.getUserId());
        return BaseResponse.newSuccess(skTreasureBoxService.getCoin(params));
    }

    /**
     * 未领取宝箱列表
     * @return
     */
    @GetMapping("/noGetList")
    public BaseResponse noGetList() {
        if (UserContextHolder.getUserId() <= 0) {
            return BaseResponse.newFailure(ResponseCode.REQUIRED_PARAMS_MISSING);
        }
        return BaseResponse.newSuccess(skTreasureBoxService.noGetList(UserContextHolder.getUserId()));
    }
}
