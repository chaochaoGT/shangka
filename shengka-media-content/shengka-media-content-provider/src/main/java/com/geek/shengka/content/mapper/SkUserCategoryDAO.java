package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkUserCategory;
import org.springframework.stereotype.Repository;

/**
 * SkUserCategoryDAO继承基类
 */
@Repository
public interface SkUserCategoryDAO extends MyBatisBaseDao<SkUserCategory, Long> {
}