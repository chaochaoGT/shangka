package com.geek.shengka.backend.service;

import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkTopicVideoListReqParam;
import com.geek.shengka.backend.params.res.SkContentVideoInfoResParam;
import com.geek.shengka.backend.util.PageVO;

/**
 * @author qubianzhong
 * @date 2019/8/1 19:51
 */
public interface SkTopicVideoService {
    /**
     * 根据topicId分页查询
     *
     * @param reqParam
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkTopicVideoListResParam>
     * @author qubianzhong
     * @Date 20:38 2019/8/1
     */
    PageVO<SkContentVideoInfoResParam> list(SkTopicVideoListReqParam reqParam) throws BaseException;
}
