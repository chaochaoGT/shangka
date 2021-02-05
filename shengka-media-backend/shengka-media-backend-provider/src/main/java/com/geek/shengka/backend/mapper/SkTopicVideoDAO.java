package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkTopicVideo;
import com.geek.shengka.backend.params.req.SkTopicVideoListReqParam;
import com.geek.shengka.backend.params.res.SkTopicVideoListResParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkTopicVideoDAO继承基类
 */
@Repository
public interface SkTopicVideoDAO extends MyBatisBaseDao<SkTopicVideo, Long> {
    /**
     * 批量插入
     *
     * @param topicVideoList
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 18:01 2019/8/1
     */
    int insertSelectiveList(@Param("list") List<SkTopicVideo> topicVideoList);

    /**
     * 根据话题ID来删除对应的视频
     *
     * @param id
     * @return int
     * @author qubianzhong
     * @Date 18:45 2019/8/1
     */
    int deleteByTopicId(Long id);

    /**
     * 列表
     *
     * @param reqParam
     * @return java.util.List<com.geek.shengka.backend.params.res.SkTopicVideoListResParam>
     * @author qubianzhong
     * @Date 20:52 2019/8/1
     */
    List<SkTopicVideoListResParam> list(SkTopicVideoListReqParam reqParam);

    /**
     * ids 列表
     *
     * @param reqParam
     * @return java.util.List<java.lang.Long>
     * @author qubianzhong
     * @Date 15:25 2019/8/6
     */
    List<String> listVideoIds(SkTopicVideoListReqParam reqParam);

    /**
     * 通过话题ID查询关联的视频数量
     *
     * @param id
     * @return int
     * @author qubianzhong
     * @Date 9:39 2019/8/21
     */
    int countByTopicId(Long id);
}