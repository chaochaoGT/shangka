package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkChannelCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * SkChannelCategoryDAO继承基类
 */
@Repository
public interface SkChannelCategoryDAO extends MyBatisBaseDao<SkChannelCategory, Long> {
}