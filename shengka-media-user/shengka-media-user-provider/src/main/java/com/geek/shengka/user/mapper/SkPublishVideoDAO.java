package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkPublishVideo;
import com.geek.shengka.user.entity.vo.SkCateMediaInfoVO;
import com.geek.shengka.user.entity.vo.SkPublishVideoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * SkPublishVideoDAO继承基类
 */
@Repository
public interface SkPublishVideoDAO extends MyBatisBaseDao<SkPublishVideo, String> {
    List<SkPublishVideo> selectPublishInfosByVids(@Param("vids") List<String> vids);

    /**
     * 当前用户发布的所有视频
     * @param paraMap
     * @return
     */
    List<SkPublishVideoVO> publishMediaListByUserId(Map<String,Object> paraMap);

    /**
     * 当前用户的视频关注和喜欢
     * @param vids
     * @param userId
     * @return
     */
    List<SkCateMediaInfoVO> selectLikeAndFans(@Param("vids") List<String> vids, Long userId);
}