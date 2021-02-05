package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkRankMapping;
import com.geek.shengka.backend.params.req.SkRankMappingAddReqParam;
import com.geek.shengka.backend.params.req.SkRankMappingListReqParam;
import com.geek.shengka.backend.params.res.SkRankMappingResParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkRankMappingDAO extends MyBatisBaseDao<SkRankMapping, Long> {

    /**
     * 批量新增
     *
     * @param rankMappings
     * @return int
     * @author qubianzhong
     * @Date 11:50 2019/8/2
     */
    int insertSelectiveList(@Param("list") List<SkRankMappingAddReqParam> rankMappings);

    /**
     * 列表
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkRankMappingResParam>
     * @author qubianzhong
     * @Date 13:41 2019/8/2
     */
    List<SkRankMappingResParam> list(SkRankMappingListReqParam param);

    /**
     * 列表IDS
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkRankMappingResParam>
     * @author qubianzhong
     * @Date 13:41 2019/8/2
     */
    List<String> listIds(SkRankMappingListReqParam param);

    /**
     * 根据榜单ID，删除所有关联的视频
     *
     * @param rankId
     * @return int
     * @author qubianzhong
     * @Date 14:06 2019/8/2
     */
    int deleteByRankId(Long rankId);
}