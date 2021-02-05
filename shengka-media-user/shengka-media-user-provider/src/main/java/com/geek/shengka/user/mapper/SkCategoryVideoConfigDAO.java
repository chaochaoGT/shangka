package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkCategoryVideoConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkCategoryVideoConfigDAO继承基类
 */
@Repository
public interface SkCategoryVideoConfigDAO extends MyBatisBaseDao<SkCategoryVideoConfig, Long> {
    /**
     * 分页频道顶部视频
     * @param startRecordNumb
     * @param pageCount
     * @return
     */
    List<SkCategoryVideoConfig> getTopMediaList(@Param("startRecordNumb") int startRecordNumb,@Param("pageCount") int pageCount);
}