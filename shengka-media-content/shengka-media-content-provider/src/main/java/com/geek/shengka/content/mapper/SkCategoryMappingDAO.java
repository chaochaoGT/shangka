package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkCategoryMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkCategoryMappingDAO继承基类
 */
@Repository
public interface SkCategoryMappingDAO extends MyBatisBaseDao<SkCategoryMapping, Long> {

    List<Long> selectContentCategoryIdByCategorId(@Param("categoryId") Long categoryId);

    /**根据频道id查询内容中心ids
     * @param categoryId
     * @return
     */
    List<String> selectByParams(@Param("categoryId") Long categoryId);

}