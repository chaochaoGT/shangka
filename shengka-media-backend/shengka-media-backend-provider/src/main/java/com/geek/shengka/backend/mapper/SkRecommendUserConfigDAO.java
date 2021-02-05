package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkRecommendUserConfig;
import com.geek.shengka.backend.params.req.SkRecommendUserConfigListReqParam;
import com.geek.shengka.backend.params.res.SkRecommendUserConfigListResParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qubianzhong
 * @Date 13:40 2019/8/22
 */
@Repository
public interface SkRecommendUserConfigDAO extends MyBatisBaseDao<SkRecommendUserConfig, Long> {
    /**
     * 列表
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkRecommendUserConfigListResParam>
     * @author qubianzhong
     * @Date 15:09 2019/8/22
     */
    List<SkRecommendUserConfigListResParam> list(SkRecommendUserConfigListReqParam param);

    /**
     * 通过用户ID查询数量
     *
     * @param userId
     * @return int
     * @author qubianzhong
     * @Date 18:20 2019/8/22
     */
    int count(@Param("userId") Long userId);
}