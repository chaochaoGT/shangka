package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkSearchPageConfig;
import com.geek.shengka.backend.params.res.SkSearchPageConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkSearchPageConfigListResParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkSearchPageConfigDAO extends MyBatisBaseDao<SkSearchPageConfig, Long> {
    /**
     * 列表
     *
     * @param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkSearchPageConfigListResParam>
     * @author qubianzhong
     * @Date 10:53 2019/8/5
     */
    List<SkSearchPageConfigListResParam> list();

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkSearchPageConfigInfoResParam
     * @author qubianzhong
     * @Date 11:21 2019/8/5
     */
    SkSearchPageConfigInfoResParam info(Long id);
}