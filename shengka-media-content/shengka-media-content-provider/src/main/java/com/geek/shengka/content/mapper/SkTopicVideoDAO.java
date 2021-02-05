package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkTopicVideo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkTopicVideoDAO继承基类
 */
@Repository
public interface SkTopicVideoDAO extends MyBatisBaseDao<SkTopicVideo, Long> {
    /**
     * 话题对应的视频
     * @param sourceId
     * @param startRecordNumb
     * @param pageCount
     * @return
     */
    List<String> selectVsById(@Param("topicId") String sourceId,@Param("startRecordNumb") int startRecordNumb,@Param("pageCount") int pageCount);

    /**
     * 查询视频关联的话题
     * @param videoId
     * @return
     */
    List<SkTopicVideo> selectByVideoId(@Param("videoId") String videoId);
}