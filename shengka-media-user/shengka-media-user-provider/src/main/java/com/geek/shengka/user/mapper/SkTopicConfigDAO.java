package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkTopicConfig;
import org.springframework.stereotype.Repository;

/**
 * SkTopicConfigDAO继承基类
 */
@Repository
public interface SkTopicConfigDAO extends MyBatisBaseDao<SkTopicConfig, Long> {
}