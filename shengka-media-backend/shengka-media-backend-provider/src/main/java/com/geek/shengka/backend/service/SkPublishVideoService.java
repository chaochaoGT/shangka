package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SkPublishVideoListReqParam;
import com.geek.shengka.backend.params.res.SkPublishVideoListResParam;
import com.geek.shengka.backend.util.PageVO;

/**
 * @author qubianzhong
 * @date 2019/8/20 15:29
 */
public interface SkPublishVideoService {
    /**
     * 根据视频标题进行分页查询
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkPublishVideoListResParam>
     * @author qubianzhong
     * @Date 15:51 2019/8/20
     */
    PageVO<SkPublishVideoListResParam> list(SkPublishVideoListReqParam param);
}
