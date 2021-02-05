package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkTopicConfig;
import com.geek.shengka.backend.params.req.SkTopicConfigListReqParam;
import com.geek.shengka.backend.params.res.SkTopicConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkTopicConfigListResParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkTopicConfigDAO继承基类
 */
@Repository
public interface SkTopicConfigDAO extends MyBatisBaseDao<SkTopicConfig, Long> {
    /**
     * 列表
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkTopicConfigListResParam>
     * @author qubianzhong
     * @Date 17:28 2019/8/1
     */
    List<SkTopicConfigListResParam> list(SkTopicConfigListReqParam param);

    /**
     * 根据话题名称查询总数
     *
     * @param topicName
     * @return int
     * @author qubianzhong
     * @Date 18:17 2019/8/1
     */
    Long countByTopicName(String topicName);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkTopicConfigInfoResParam
     * @author qubianzhong
     * @Date 15:16 2019/8/5
     */
    SkTopicConfigInfoResParam info(Long id);

    /**
     * 通过IDs查询列表
     *
     * @param topicIds
     * @return java.util.List<com.geek.shengka.backend.entity.SkTopicVideo>
     * @author qubianzhong
     * @Date 16:02 2019/8/6
     */
    List<SkTopicConfig> getInfosByIds(@Param("ids") List<Long> topicIds);
}