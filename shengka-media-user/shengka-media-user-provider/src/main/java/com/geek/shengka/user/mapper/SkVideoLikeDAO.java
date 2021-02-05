package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkVideoLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkVideoLikeDAO继承基类
 */
@Repository
public interface SkVideoLikeDAO extends MyBatisBaseDao<SkVideoLike, Long> {
    /**
     * 分页获取喜欢视频
     * @param userId
     * @param startRecordNumb
     * @param pageCount
     * @return
     */
    List<String> myVideoLikeList(@Param("userId") Long userId,@Param("startRecordNumb") Integer startRecordNumb, @Param("pageCount")Integer pageCount);

    /**
     * 根据视频ids获取当前用户喜欢的vids
     * @param userId
     * @param vids
     * @return
     */
    List<String> myLikeVideos(@Param("userId") Long userId, @Param("vids")List<String> vids);
}