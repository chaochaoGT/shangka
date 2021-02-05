package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * SkReportDAO继承基类
 */
@Repository
public interface SkReportDAO extends MyBatisBaseDao<SkReport, Long> {

    /**
     * 根据uid，videoid查询数据
     * @param userId
     * @param videoId
     * @return
     */
    SkReport selectByUidAndVideoId(@Param("userId") Long userId,@Param("videoId") String videoId);
}