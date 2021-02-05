package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * SkCategoryDAO继承基类
 */
@Repository
public interface SkCategoryDAO extends MyBatisBaseDao<SkCategory, Long> {
    List<SkCategory> categoryList(Map<String,Object> paraMap);

    SkCategory selectByParams(@Param("categoryId") Long categoryId, @Param("channel") String channel);

}