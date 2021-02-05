package com.geek.shengka.content.service;

import com.geek.shengka.content.entity.vo.SkSearchSourceVO;
import com.geek.shengka.content.entity.vo.SuperVO;
import com.geek.shengka.content.request.SearchRequest;
import com.geek.shengka.content.response.TopicConfigResponse;

import java.util.List;

/**
 * 话题列表查询
 * @author: yunfei
 * @create: 2019-07-31 18:41
 **/
public interface SkTopicConfigService {
   public List<TopicConfigResponse> listConfig(Long userId);

    /**
     * 话题详情页+话题对应视频list
     * @param param
     * @return
     */
    SuperVO topicInfo(SearchRequest param);

    /**
     * 话题视频列表分页
     * @param param
     * @return
     */
    List<SkSearchSourceVO> topicVideoList(SearchRequest param);
}
