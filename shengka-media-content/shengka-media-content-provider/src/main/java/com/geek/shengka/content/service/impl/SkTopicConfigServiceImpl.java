package com.geek.shengka.content.service.impl;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.content.entity.SkTopicConfig;
import com.geek.shengka.content.entity.vo.SkSearchSourceVO;
import com.geek.shengka.content.entity.vo.SuperVO;
import com.geek.shengka.content.mapper.SkTopicConfigDAO;
import com.geek.shengka.content.mapper.SkTopicVideoDAO;
import com.geek.shengka.content.request.SearchRequest;
import com.geek.shengka.content.response.TopicConfigResponse;
import com.geek.shengka.content.service.SkSearchService;
import com.geek.shengka.content.service.SkTopicConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 话题列表查询
 *
 * @author: yunfei
 * @create: 2019-07-31 18:42
 **/
@Service
public class SkTopicConfigServiceImpl implements SkTopicConfigService {
    private Logger logger = LoggerFactory.getLogger(SkTopicConfigServiceImpl.class);

    @Autowired
    SkTopicConfigDAO skTopicConfigDAO;

    @Autowired
    SkSearchService searchService;

    @Autowired
    private SkTopicVideoDAO skTopicVideoDAO;

    @Override
    public List<TopicConfigResponse> listConfig(Long userId) {
        List<SkTopicConfig> list=skTopicConfigDAO.listConfig(userId);
        return list.stream().map(skTopicConfig -> translation(skTopicConfig)).collect(Collectors.toList());
    }

    /**
     * 转换vo
     * @param skTopicConfig
     * @return
     */
    private final TopicConfigResponse translation(SkTopicConfig skTopicConfig) {
        TopicConfigResponse response=new TopicConfigResponse();
        response.setTopicId(skTopicConfig.getId());
        response.setTopicName(skTopicConfig.getTopicName());
        response.setTopicLogo(skTopicConfig.getTopicLogo());
        return response;
    }

    @Override
    public SuperVO  topicInfo(SearchRequest param) {
        logger.debug("topicInfo is param !!!  {}", JSON.toJSONString(param));

        //话题详情
        SkSearchSourceVO vo = skTopicConfigDAO.selectById(Long.valueOf(param.getSourceId()), Objects.isNull(param.getUserId())?0:param.getUserId());
        if (null ==vo){
            logger.warn("topicInfo is empty !!!  param={}", JSON.toJSONString(param));
            return new SuperVO();
        }
        //话题下的vids
        List<String> vids=skTopicVideoDAO.selectVsById(param.getSourceId(),param.getStartRecordNumb(),param.getPageCount());
        List<SkSearchSourceVO> manyMedias = searchService.getManyMedias(vids);
        SuperVO reuslt=new SuperVO();
        reuslt.put("topicInfo",vo);
        reuslt.put("videoList",manyMedias );
        return reuslt;
    }

    @Override
    public List<SkSearchSourceVO> topicVideoList(SearchRequest param) {
        try {
            List<String> vids=skTopicVideoDAO.selectVsById(param.getSourceId(),param.getStartRecordNumb(),param.getPageCount());
            return searchService.getManyMedias(vids);
        } catch (Exception e) {
            logger.error("topicVideoList is error params={} msg={} e={}",JSON.toJSONString(param),e.getMessage(),e);
            return new ArrayList<>(0);
        }
    }
}
