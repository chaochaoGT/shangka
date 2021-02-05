package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkPublishVideo;
import com.geek.shengka.content.entity.vo.SkCateMediaInfoVO;
import com.geek.shengka.content.entity.vo.SkMediaInfoVO;
import com.geek.shengka.content.entity.vo.UpdatePublishVideoCreateTime;
import com.geek.shengka.content.request.SearchRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkPublishVideoDAO继承基类
 */
@Repository
public interface SkPublishVideoDAO extends MyBatisBaseDao<SkPublishVideo, String> {
    List<String> searchVideoIdsByKeyWord(SearchRequest paraMap);

    /**
     * 根据watchmode 和category 查询视频
     * @param watchMode
     * @param categoryId
     * @return
     */
    List<String> selectByCategoryAndWatchMode(@Param("watchMode") Integer watchMode,@Param("categoryIds") List<Long> categoryIds);

    /**
     * 根据视频ids查询详情
     * @param vids
     * @return
     */
    List<SkCateMediaInfoVO> selectPublishInfosByVids(@Param("vids") List<String> vids,@Param("userId") Long userId);
    
    
    /**
     * 根据视频ids查询详情
     * @param vids
     * @return
     */
    List<SkMediaInfoVO> selectByVideoIds(@Param("videoIds") List<String> videoIds);
    
    List<String> selectIdsByIds(@Param("ids") List<String> ids);
    
    int updateCreateTime(UpdatePublishVideoCreateTime record);
}