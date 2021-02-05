package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkCategoryVideoConfig;
import com.geek.shengka.backend.params.res.SkCategoryVideoConfigListResParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qubianzhong
 * @Date 13:39 2019/8/22
 */
@Repository
public interface SkCategoryVideoConfigDAO extends MyBatisBaseDao<SkCategoryVideoConfig, Long> {
    /**
     * 列表
     *
     * @param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkCategoryVideoConfigListResParam>
     * @author qubianzhong
     * @Date 14:02 2019/8/22
     */
    List<SkCategoryVideoConfigListResParam> list();
}