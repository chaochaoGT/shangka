package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SkSearchPageConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkSearchPageConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkSearchPageConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkSearchPageConfigListResParam;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/5 9:47
 */
public interface SkSearchPageConfigService {
    /**
     * 搜索页配置列表--不分页
     *
     * @param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkSearchPageConfigListResParam>
     * @author qubianzhong
     * @Date 10:48 2019/8/5
     */
    List<SkSearchPageConfigListResParam> list();

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 10:49 2019/8/5
     */
    Boolean add(SkSearchPageConfigAddReqParam add);

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 10:49 2019/8/5
     */
    Boolean update(SkSearchPageConfigUpdateReqParam update);

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 10:49 2019/8/5
     */
    Boolean delete(Long id);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkSearchPageConfigInfoResParam
     * @author qubianzhong
     * @Date 11:20 2019/8/5
     */
    SkSearchPageConfigInfoResParam info(Long id);
}
