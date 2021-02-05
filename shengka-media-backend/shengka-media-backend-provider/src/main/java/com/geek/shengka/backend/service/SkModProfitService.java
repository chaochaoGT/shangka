package com.geek.shengka.backend.service;

import com.geek.shengka.backend.params.req.SkModProfitAddReqParam;
import com.geek.shengka.backend.params.req.SkModProfitListReqParam;
import com.geek.shengka.backend.params.req.SkModProfitUpdateReqParam;
import com.geek.shengka.backend.params.res.SkModProfitResParam;
import com.geek.shengka.backend.util.PageVO;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/26 10:36
 */
public interface SkModProfitService {
    /**
     * 分页查询
     *
     * @param param
     * @return com.geek.core.common.v2.PageBaseV2ApiResponse
     * @author qubianzhong
     * @Date 10:47 2019/8/26
     */
    PageVO<SkModProfitResParam> list(SkModProfitListReqParam param);

    /**
     * 新增
     *
     * @param addDTO
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 10:56 2019/8/26
     */
    Boolean add(SkModProfitAddReqParam addDTO);

    /**
     * 更新
     *
     * @param updateDTO
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 11:00 2019/8/26
     */
    Boolean update(SkModProfitUpdateReqParam updateDTO);

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 11:02 2019/8/26
     */
    Boolean delete(Long id);

    /**
     * 批量插入
     *
     * @param skModProfitList
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 11:54 2019/8/29
     */
    Boolean uploadData(List<SkModProfitAddReqParam> skModProfitList);
}
