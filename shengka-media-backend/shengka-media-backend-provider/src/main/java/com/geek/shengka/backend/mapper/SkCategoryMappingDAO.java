package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkCategoryMapping;
import com.geek.shengka.backend.params.req.SkCategoryMappingAddReqParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkCategoryMappingDAO继承基类
 */
@Repository
public interface SkCategoryMappingDAO extends MyBatisBaseDao<SkCategoryMapping, Long> {
    /**
     * 根据频道ID来查询列表
     *
     * @param categoryId
     * @return java.util.List<com.geek.shengka.backend.entity.SkCategoryMapping>
     * @author qubianzhong
     * @Date 18:40 2019/7/31
     */
    List<SkCategoryMapping> listByCategoryId(Long categoryId);

    /**
     * 根据频道ID来进行删除
     *
     * @param categoryId
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:40 2019/7/31
     */
    Boolean deleteByCategoryId(Long categoryId);

    /**
    *  批量插入
    *
    * @author qubianzhong
    * @Date 20:38 2019/7/31
    * @param categoryMappings
    * @return int
    */
    int insertSelectiveList(@Param("list") List<SkCategoryMappingAddReqParam> categoryMappings);
}