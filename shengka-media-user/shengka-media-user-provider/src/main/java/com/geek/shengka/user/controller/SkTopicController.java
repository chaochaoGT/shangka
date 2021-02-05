package com.geek.shengka.user.controller;

import com.geek.shengka.common.annotation.OnlyUserIgnoreToken;
import com.geek.shengka.common.basemodel.BaseResponse;
import com.geek.shengka.common.basemodel.ResponseBeanCode;
import com.geek.shengka.common.context.BaseContextHandler;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.user.service.SkTopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Filename: SkCategoryController
 * @Description:  频道
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/7/31 ;
 */
@RestController
@RequestMapping("v1/app/user/topic")
public class SkTopicController {
    private Logger logger= LoggerFactory.getLogger(SkTopicController.class);

    @Autowired
    private SkTopicService skTopicService;

    /**
     * 订阅/取消订阅 话题
     * @param topicId
     * @param status
     * @return
     */
    @GetMapping("/subscripTopic")
    public BaseResponse subscripTopic(@RequestParam("topicId") Long topicId, @RequestParam("status") Integer status){
        logger.debug("subscripTopic  topicId={} status={}",topicId,status);
        if (0==topicId || null==status){
            return BaseResponse.newFailure(ResponseBeanCode.REQUIRED_PARAMS_MISSING.getCode(), ResponseBeanCode.REQUIRED_PARAMS_MISSING.getMessage());
        }
        Long userId = UserContextHolder.getUserId();
        skTopicService.subscripTopic(userId,topicId,status);
        return BaseResponse.newSuccess();
    }

    /**
     * 我关注的话题列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/attentionTopicList")
    public BaseResponse attentionTopicList(int pageIndex, int pageSize){
        String userId = BaseContextHandler.getCurrentUId();
        return BaseResponse.newSuccess(skTopicService.attentionTopicList(Long.valueOf(userId),pageIndex,pageSize));
    }

}
