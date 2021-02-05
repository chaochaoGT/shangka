package com.geek.shengka.user.service;

import com.geek.shengka.common.basemodel.PageRequest;
import com.geek.shengka.user.entity.vo.SkTopicConfigVO;

import java.util.List;

/**
 * @Filename: SkTopicService
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/8 ;
 */
public interface SkTopicService {
    /**
     * 订阅/取消订阅 话题
     * @param topicId
     * @param status
     * @return
     */
    void subscripTopic(Long userId, Long topicId, Integer status);

    /**
     * 我关注的话题列表
     * @param userId
     * @param params
     * @return
     */
    List<SkTopicConfigVO> attentionTopicList(Long userId, int pageIndex, int pageSize);
}
