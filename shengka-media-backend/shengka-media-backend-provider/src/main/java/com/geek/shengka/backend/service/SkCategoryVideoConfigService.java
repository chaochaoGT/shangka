package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SkCategoryVideoConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkCategoryVideoConfigListReqParam;
import com.geek.shengka.backend.params.req.SkCategoryVideoConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkCategoryVideoConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkCategoryVideoConfigListResParam;
import com.geek.shengka.backend.util.PageVO;

/**
 * @author qubianzhong
 * @date 2019/8/22 13:44
 */
public interface SkCategoryVideoConfigService {
    /**
     * 列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkCategoryVideoConfigListResParam>
     * @author qubianzhong
     * @Date 13:49 2019/8/22
     */
    PageVO<SkCategoryVideoConfigListResParam> list(SkCategoryVideoConfigListReqParam param);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkCategoryVideoConfigInfoResParam
     * @author qubianzhong
     * @Date 13:51 2019/8/22
     */
    SkCategoryVideoConfigInfoResParam info(Long id);

    /**
     * 新增
     *
     * @param addReqParam
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 13:55 2019/8/22
     */
    Boolean add(SkCategoryVideoConfigAddReqParam addReqParam);

    /**
     * 更新
     *
     * @param updateReqParam
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 13:55 2019/8/22
     */
    Boolean update(SkCategoryVideoConfigUpdateReqParam updateReqParam);

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 13:56 2019/8/22
     */
    Boolean delete(Long id);
}
