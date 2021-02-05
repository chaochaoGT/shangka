package com.geek.shengka.backend.service;

import com.geek.shengka.backend.entity.SkTopicConfig;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkRankMappingListReqParam;
import com.geek.shengka.backend.params.res.SkContentVideoInfoResParam;
import com.geek.shengka.backend.params.res.SkUserInfoResParam;
import com.geek.shengka.backend.util.PageVO;

/**
 * @author qubianzhong
 * @date 2019/8/2 10:54
 */
public interface SkRankMappingService {
    /**
     * 视频列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkRankMappingResParam>
     * @author qubianzhong
     * @Date 13:40 2019/8/2
     */
    PageVO<SkContentVideoInfoResParam> videoList(SkRankMappingListReqParam param) throws BaseException;

    /**
    *  话题列表
    *
    * @author qubianzhong
    * @Date 15:48 2019/8/6
    * @param param
    * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkRankMappingResParam>
    */
    PageVO<SkTopicConfig> topicList(SkRankMappingListReqParam param);

    /**
    *  用户列表
    *
    * @author qubianzhong
    * @Date 15:49 2019/8/6
    * @param param
    * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkRankMappingResParam>
    */
    PageVO<SkUserInfoResParam> userList(SkRankMappingListReqParam param) throws BaseException;
}
