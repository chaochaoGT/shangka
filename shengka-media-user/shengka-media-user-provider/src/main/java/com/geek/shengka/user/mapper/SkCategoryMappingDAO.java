package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkCategoryMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkCategoryMappingDAO继承基类
 */
@Repository
public interface SkCategoryMappingDAO extends MyBatisBaseDao<SkCategoryMapping, Long> {
    List<String> selectByParams(@Param("categoryId") Long categoryId);


}