package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SkRankAddReqParam;
import com.geek.shengka.backend.params.req.SkRankEnableReqParam;
import com.geek.shengka.backend.params.req.SkRankListReqParam;
import com.geek.shengka.backend.params.req.SkRankUpdateReqParam;
import com.geek.shengka.backend.params.res.SkRankInfoResParam;
import com.geek.shengka.backend.params.res.SkRankListResParam;
import com.geek.shengka.backend.util.PageVO;

/**
 * @author qubianzhong
 * @date 2019/8/2 10:54
 */
public interface SkRankListService {
    /**
     * 列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkRankListResParam>
     * @author qubianzhong
     * @Date 11:14 2019/8/2
     */
    PageVO<SkRankListResParam> list(SkRankListReqParam param);

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 11:28 2019/8/2
     */
    Boolean add(SkRankAddReqParam add);

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 11:28 2019/8/2
     */
    Boolean update(SkRankUpdateReqParam update);

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 11:28 2019/8/2
     */
    Boolean delete(Long id);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkRankInfoResParam
     * @author qubianzhong
     * @Date 14:46 2019/8/5
     */
    SkRankInfoResParam info(Long id);

    /**
     * 开关
     *
     * @param enable
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:27 2019/8/6
     */
    Boolean enable(SkRankEnableReqParam enable);
}
