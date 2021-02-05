package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkWatchHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkWatchHistoryDAO继承基类
 * @author yunfei
 */
@Repository
public interface SkWatchHistoryDAO extends MyBatisBaseDao<SkWatchHistory, Long> {

    /***
     * 查询观看历史
     * @param userId
     * @param start
     * @param size
     * @return
     */
    List<SkWatchHistory> selectUserWatchHistory(@Param("userId") Long userId,@Param("start") Integer start,@Param("size") Integer size);

    /***
     * 删除观看历史
     * @param userId
     * @param videoIds
     * @return
     */
    int delUserWatchHistory(@Param("userId") Long userId,@Param("videoIds") List<String> videoIds);

    /**
     * 根据uid和videoId查询观看历史
     * @param userId
     * @param videoId
     * @return
     */
    SkWatchHistory selectByUidAndVideoId(@Param("userId") Long userId,@Param("videoId") String videoId);
}