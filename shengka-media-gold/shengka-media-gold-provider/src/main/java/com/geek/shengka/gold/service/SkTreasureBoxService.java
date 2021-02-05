package com.geek.shengka.gold.service;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.gold.request.OpenBoxRequest;
import com.geek.shengka.gold.request.TreasureBoxRequest;
import com.geek.shengka.gold.response.TreasureBoxConfigResponse;
import com.geek.shengka.gold.response.UserTreasureBoxResponse;

import java.util.List;

/**
 * @author: yunfei
 * @create: 2019-08-01 15:03
 **/
public interface SkTreasureBoxService {

    /***
     * 获取宝箱配置 当天剩余次数
     * @param userId
     * @return
     */
    TreasureBoxConfigResponse config(Long userId);

    /**
     * 开启宝箱
     *
     * @param params
     * @return
     */
    BaseResponse open(OpenBoxRequest params);

    /**
     * 领取宝箱里的金币
     *
     * @param params
     * @return coinNum
     */
    int getCoin(TreasureBoxRequest params);

    /**
     * 用户未领取的宝箱列表
     * @param userId
     * @return
     */
    List<UserTreasureBoxResponse> noGetList(Long userId);
}
