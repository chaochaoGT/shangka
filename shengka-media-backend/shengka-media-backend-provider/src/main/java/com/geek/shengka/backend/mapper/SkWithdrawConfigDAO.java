package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkWithdrawConfig;
import com.geek.shengka.backend.params.req.SkWithdrawConfigListReqParam;
import com.geek.shengka.backend.params.res.SkWithdrawConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkWithdrawConfigListResParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkWithdrawConfigDAO extends MyBatisBaseDao<SkWithdrawConfig, Long> {
    /**
     * 列表
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkWithdrawConfigListResParam>
     * @author qubianzhong
     * @Date 18:20 2019/8/2
     */
    List<SkWithdrawConfigListResParam> list(SkWithdrawConfigListReqParam param);

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