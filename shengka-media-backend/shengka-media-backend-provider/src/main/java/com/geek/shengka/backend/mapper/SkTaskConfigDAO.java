package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkTaskConfig;
import com.geek.shengka.backend.params.req.SkTaskConfigListReqParam;
import com.geek.shengka.backend.params.res.SkTaskConfigInfoResParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkTaskConfigDAO extends MyBatisBaseDao<SkTaskConfig, Long> {

    /**
     * 列表
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkTaskConfigInfoResParam>
     * @author qubianzhong
     * @Date 17:47 2019/8/2
     */
    List<SkTaskConfigInfoResParam> list(SkTaskConfigListReqParam param);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkTaskConfigInfoResParam
     * @author qubianzhong
     * @Date 15:05 2019/8/5
     */
    SkTaskConfigInfoResParam info(Long id);
}