package com.geek.shengka.backend.service;

import com.geek.shengka.backend.entity.SkTopicConfig;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkSearchPageConfigMappingListReqParam;
import com.geek.shengka.backend.params.res.SkContentVideoInfoResParam;
import com.geek.shengka.backend.util.PageVO;

/**
 * @author qubianzhong
 * @date 2019/8/7 14:18
 */
public interface SkSearchPageConfigMappingService {
    /**
     * 视频列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkContentVideoInfoResParam>
     * @author qubianzhong
     * @Date 14:23 2019/8/7
     */
    PageVO<SkContentVideoInfoResParam> videoList(SkSearchPageConfigMappingListReqParam param) throws BaseException;

    /**
     * 话题列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.entity.SkTopicConfig>
     * @author qubianzhong
     * @Date 14:23 2019/8/7
     */
    PageVO<SkTopicConfig> topicList(SkSearchPageConfigMappingListReqParam param);
}
