package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkChannel;
import org.springframework.stereotype.Repository;

/**
 * SkChannelDAO继承基类
 */
@Repository
public interface SkChannelDAO extends MyBatisBaseDao<SkChannel, Long> {
}