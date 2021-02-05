package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SkVersionAddReqParam;
import com.geek.shengka.backend.params.req.SkVersionListReqParam;
import com.geek.shengka.backend.params.req.SkVersionUpdateReqParam;
import com.geek.shengka.backend.params.res.SkVersionInfoResParam;
import com.geek.shengka.backend.params.res.SkVersionListResParam;
import com.geek.shengka.backend.util.PageVO;

/**
 * @author qubianzhong
 * @date 2019/8/1 13:44
 */
public interface SkVersionService {
    /**
     * 列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkVersionListResParam>
     * @author qubianzhong
     * @Date 14:03 2019/8/1
     */
    PageVO<SkVersionListResParam> list(SkVersionListReqParam param);

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 14:03 2019/8/1
     */
    Boolean add(SkVersionAddReqParam add);

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 14:03 2019/8/1
     */
    Boolean update(SkVersionUpdateReqParam update);

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 14:03 2019/8/1
     */
    Boolean delete(Long id);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkVersionInfoResParam
     * @author qubianzhong
     * @Date 15:49 2019/8/5
     */
    SkVersionInfoResParam info(Long id);
}
