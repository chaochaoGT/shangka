package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkCategory;
import com.geek.shengka.backend.params.req.SkCategoryListReqParam;
import com.geek.shengka.backend.params.res.SkCategoryInfoResParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkCategoryDAO继承基类
 */
@Repository
public interface SkCategoryDAO extends MyBatisBaseDao<SkCategory, Long> {
    /**
     * 查询列表
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.entity.SkCategory>
     * @author qubianzhong
     * @Date 17:41 2019/7/31
     */
    List<SkCategoryInfoResParam> list(SkCategoryListReqParam param);

    /**
     * 查询列表的总数
     *
     * @param param
     * @return int
     * @author qubianzhong
     * @Date 10:56 2019/8/1
     */
    int listCount(SkCategoryListReqParam param);

    /**
     * info
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkCategoryInfoResParam
     * @author qubianzhong
     * @Date 14:00 2019/8/5
     */
    SkCategoryInfoResParam info(Long id);
}