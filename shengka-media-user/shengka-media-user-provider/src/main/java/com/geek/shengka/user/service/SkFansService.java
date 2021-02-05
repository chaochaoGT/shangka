package com.geek.shengka.user.service;

import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.user.entity.vo.SkFansVO;
import com.geek.shengka.user.request.AttentionRequest;
import com.geek.shengka.user.request.FansRequest;

import java.util.List;

/**
 * 用户互粉业务
 * @author: yunfei
 * @create: 2019-08-01 11:25
 **/
public interface SkFansService {

    /**
     * 关注/取消关注
     * @param params
     * @return
     */
    BaseResponse attentionOrNot(AttentionRequest params);

    /**
     * 当前用户关注列表和粉丝列表
     * @param params
     * @return
     */
    List<SkFansVO> attentionFansList(FansRequest params);

    /**
     * 关注用户搜索
     * @param params
     * @return
     */
    List<SkFansVO> findFansList(FansRequest params);

    /**
     * 别人的关注列表和粉丝列表
     * @param params
     * @param loginUserId
     * @return
     */
    List<SkFansVO> otterAttentionFansList(FansRequest params, Long loginUserId);
}
