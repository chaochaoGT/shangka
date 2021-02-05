package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkRankList;
import com.geek.shengka.backend.params.req.SkRankListReqParam;
import com.geek.shengka.backend.params.res.SkRankInfoResParam;
import com.geek.shengka.backend.params.res.SkRankListResParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkRankListDAO extends MyBatisBaseDao<SkRankList, Long> {

    /**
     * 列表
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkRankListResParam>
     * @author qubianzhong
     * @Date 11:34 2019/8/2
     */
    List<SkRankListResParam> list(SkRankListReqParam param);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkRankInfoResParam
     * @author qubianzhong
     * @Date 14:47 2019/8/5
     */
    SkRankInfoResParam info(Long id);
}