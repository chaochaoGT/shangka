package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkTopicConfig;
import com.geek.shengka.content.entity.vo.SkSearchSourceVO;
import com.geek.shengka.content.request.SearchRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * SkTopicConfigDAO继承基类
 */
@Repository
public interface SkTopicConfigDAO extends MyBatisBaseDao<SkTopicConfig, Long> {

    List<SkTopicConfig> listConfig(@Param("userId") Long userId);
	
	public SkTopicConfig selectByTopicName(@Param("topicName") String topicName);
	
	public int insertTopic(SkTopicConfig skTopicConfig);

    List<SkSearchSourceVO> getHotTopicSearchs(Map<String, Object> paraMap);

    /**
     * 分页匹配话题名称
     * @param paraMap
     * @return
     */
    List<SkTopicConfig> searchTopicByKeyWord(SearchRequest paraMap);

    /**
     * 当前用户话题信息
     * @param topicId
     * @param userId
     * @return
     */
    SkSearchSourceVO selectById(@Param("topicId") Long topicId, @Param("userId")Long userId);

    /**
     * 更新观看次数
     * @param list
     * @return
     */
    int updateWatchNum(@Param("list") List<Long> list);
}