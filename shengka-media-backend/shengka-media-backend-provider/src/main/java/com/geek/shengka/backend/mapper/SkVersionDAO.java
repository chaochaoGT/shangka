package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkVersion;
import com.geek.shengka.backend.params.req.SkVersionListReqParam;
import com.geek.shengka.backend.params.res.SkVersionInfoResParam;
import com.geek.shengka.backend.params.res.SkVersionListResParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkVersionDAO继承基类
 */
@Repository
public interface SkVersionDAO extends MyBatisBaseDao<SkVersion, Long> {
    /**
     * 列表
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkVersionListResParam>
     * @author qubianzhong
     * @Date 14:27 2019/8/1
     */
    List<SkVersionListResParam> list(SkVersionListReqParam param);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkVersionInfoResParam
     * @author qubianzhong
     * @Date 15:50 2019/8/5
     */
    SkVersionInfoResParam info(Long id);
}