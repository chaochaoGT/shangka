package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkVideoLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkVideoLikeDAO继承基类
 */
@Repository
public interface SkVideoLikeDAO extends MyBatisBaseDao<SkVideoLike, Long> {

    SkVideoLike selectByUidAndVideoId(@Param("userId") Long userId, @Param("videoId") String videoId);

    /**
     * 根据视频IDS，判断当前IDS有多少是当前用户喜欢的IDS
     *
     * @param videoIds
     * @param userId
     * @return
     */
    List<String> myVideoLikeList(@Param("ids") List<String> videoIds,
                                 @Param("userId") Long userId);
}