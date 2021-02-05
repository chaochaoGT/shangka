package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkVersion;
import org.springframework.stereotype.Repository;

/**
 * SkVersionDAO继承基类
 */
@Repository
public interface SkVersionDAO extends MyBatisBaseDao<SkVersion, Long> {
}