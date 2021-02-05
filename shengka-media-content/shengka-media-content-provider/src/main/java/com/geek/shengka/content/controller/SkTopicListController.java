package com.geek.shengka.content.controller;

import com.geek.shengka.common.annotation.IgnoreClientToken;
import com.geek.shengka.common.annotation.OnlyUserIgnoreToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseBeanCode;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.content.constans.SearchConstans;
import com.geek.shengka.content.request.SearchRequest;
import com.geek.shengka.content.service.SkTopicConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 话题列表
 *
 * @author: yunfei
 * @create: 2019-07-31 18:33
 **/
@RestController
@RequestMapping("/v1/topic")
public class SkTopicListController {

    @Autowired
    private SkTopicConfigService skTopicConfigService;

    /***
     * #话题列表
     * @return
     */
    @GetMapping("/listConfig")
    @OnlyUserIgnoreToken
    public BaseResponse listConfig() {
        Long userId = UserContextHolder.getUserId();
        return BaseResponse.newSuccess(skTopicConfigService.listConfig(userId<= 0 ? null :userId));
    }

    /**
     *  话题详情
     * @param param （ meduleId    moduleCode）
     * @return
     */
    @PostMapping("/topicInfo")
    @IgnoreClientToken
    public BaseResponse topicInfo(@RequestBody SearchRequest param) {
        param.setPageCount(SearchConstans.default_pageSize_10);
        if (StringUtils.isEmpty(param.getSourceId())){
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(),ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        UserContextHolder.getDataFromHeader(param);
        return BaseResponse.newSuccess(skTopicConfigService.topicInfo(param));
    }

    /***
     * 话题的视频
     * @return
     */
    @PostMapping("/topicVideoList")
    @IgnoreClientToken
    public BaseResponse topicVideoList(@RequestBody SearchRequest param) {
        if (StringUtils.isEmpty(param.getSourceId())){
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(),ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        UserContextHolder.getDataFromHeader(param);
        return BaseResponse.newSuccess(skTopicConfigService.topicVideoList(param));
    }

}
