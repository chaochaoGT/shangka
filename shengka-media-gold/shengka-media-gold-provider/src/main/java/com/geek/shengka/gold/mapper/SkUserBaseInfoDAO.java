package com.geek.shengka.gold.mapper;

import com.geek.shengka.gold.entity.SkUserBaseInfo;
import org.springframework.stereotype.Repository;

/**
 * SkUserBaseInfoDAO继承基类
 */
@Repository
public interface SkUserBaseInfoDAO extends MyBatisBaseDao<SkUserBaseInfo, Long> {
}