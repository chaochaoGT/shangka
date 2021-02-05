package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkVersion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * SkVersionDAO继承基类
 */
@Repository
public interface SkVersionDAO extends MyBatisBaseDao<SkVersion, Long> {

    /**
     * 版本信息
     * @param channelId
     * @param prdType
     * @return
     */
    SkVersion lastVersion(@Param("channelId") Long channelId, @Param("prdType") String prdType);
}