package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SkRecommendUserConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkRecommendUserConfigListReqParam;
import com.geek.shengka.backend.params.res.SkRecommendUserConfigListResParam;
import com.geek.shengka.backend.util.PageVO;

/**
 * @author qubianzhong
 * @date 2019/8/22 13:44
 */
public interface SkRecommendUserConfigService {
    /**
     * 列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkRecommendUserConfigListResParam>
     * @author qubianzhong
     * @Date 15:06 2019/8/22
     */
    PageVO<SkRecommendUserConfigListResParam> list(SkRecommendUserConfigListReqParam param);

    /**
     * 新增
     *
     * @param addReqParam
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 15:06 2019/8/22
     */
    Boolean add(SkRecommendUserConfigAddReqParam addReqParam);

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 15:06 2019/8/22
     */
    Boolean delete(Long id);

    /**
     * 通过用户ID查询数量
     *
     * @param userId
     * @return int
     * @author qubianzhong
     * @Date 18:19 2019/8/22
     */
    int count(Long userId);
}
