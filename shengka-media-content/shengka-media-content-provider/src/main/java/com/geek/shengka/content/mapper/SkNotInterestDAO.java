package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkNotInterest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * SkNotInterestDAO继承基类
 */
@Repository
public interface SkNotInterestDAO extends MyBatisBaseDao<SkNotInterest, Long> {

    /**
     * 不感兴趣
     * @param userId
     * @param videoId
     * @return
     */
    SkNotInterest selectByUidAndVideoId(@Param("userId") Long userId,@Param("videoId") String videoId);
}