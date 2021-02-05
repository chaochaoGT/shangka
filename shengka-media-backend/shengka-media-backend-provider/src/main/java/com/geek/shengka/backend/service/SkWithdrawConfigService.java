package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SkWithdrawConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkWithdrawConfigListReqParam;
import com.geek.shengka.backend.params.req.SkWithdrawConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkWithdrawConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkWithdrawConfigListResParam;
import com.geek.shengka.backend.util.PageVO;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:20
 */
public interface SkWithdrawConfigService {
    /**
     * 列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkWithdrawConfigListResParam>
     * @author qubianzhong
     * @Date 18:17 2019/8/2
     */
    PageVO<SkWithdrawConfigListResParam> list(SkWithdrawConfigListReqParam param);

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:17 2019/8/2
     */
    Boolean add(SkWithdrawConfigAddReqParam add);

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:17 2019/8/2
     */
    Boolean update(SkWithdrawConfigUpdateReqParam update);

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:18 2019/8/2
     */
    Boolean delete(Long id);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkWithdrawConfigListResParam
     * @author qubianzhong
     * @Date 15:54 2019/8/5
     */
    SkWithdrawConfigInfoResParam info(Long id);
}
