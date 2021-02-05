package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SkTaskConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkTaskConfigListReqParam;
import com.geek.shengka.backend.params.req.SkTaskConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkTaskConfigInfoResParam;
import com.geek.shengka.backend.util.PageVO;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:19
 */
public interface SkTaskConfigService {
    /**
     * 列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkTaskConfigInfoResParam>
     * @author qubianzhong
     * @Date 17:43 2019/8/2
     */
    PageVO<SkTaskConfigInfoResParam> list(SkTaskConfigListReqParam param);

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:43 2019/8/2
     */
    Boolean add(SkTaskConfigAddReqParam add);

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:44 2019/8/2
     */
    Boolean update(SkTaskConfigUpdateReqParam update);

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:44 2019/8/2
     */
    Boolean delete(Long id);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkTaskConfigInfoResParam
     * @author qubianzhong
     * @Date 15:04 2019/8/5
     */
    SkTaskConfigInfoResParam info(Long id);
}
