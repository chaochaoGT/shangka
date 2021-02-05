package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkTopicVideo;
import com.geek.shengka.user.entity.vo.SkCateMediaInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkTopicVideoDAO继承基类
 */
@Repository
public interface SkTopicVideoDAO extends MyBatisBaseDao<SkTopicVideo, Long> {
    List<SkCateMediaInfoVO> selectTopicsByVids(@Param("vids") List<String> vids);
}