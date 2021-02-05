package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkChannel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * SkChannelDAO继承基类
 */
@Repository
public interface SkChannelDAO extends MyBatisBaseDao<SkChannel, Long> {
    /**
     * 根据渠道code查询渠道信息
     * @param channelCode
     * @return
     */
    SkChannel selectChannelByCode(@Param("channelCode") String channelCode);
}