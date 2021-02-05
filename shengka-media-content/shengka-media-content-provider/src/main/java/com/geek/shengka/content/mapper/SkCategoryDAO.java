package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * SkCategoryDAO继承基类
 */
@Repository
public interface SkCategoryDAO extends MyBatisBaseDao<SkCategory, Long> {

    SkCategory selectByParams(@Param("categoryId") Long categoryId, @Param("channel") String channel);

}