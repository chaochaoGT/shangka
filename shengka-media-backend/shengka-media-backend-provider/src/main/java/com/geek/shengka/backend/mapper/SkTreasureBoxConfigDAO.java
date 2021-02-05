package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkTreasureBoxConfig;
import com.geek.shengka.backend.params.req.SkTreasureBoxConfigListReqParam;
import com.geek.shengka.backend.params.res.SkTreasureBoxConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkTreasureBoxConfigListResParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SkTreasureBoxConfigDAO extends MyBatisBaseDao<SkTreasureBoxConfig, Long> {
    /**
     * 列表
     *
     * @param param
     * @return java.util.List<com.geek.shengka.backend.params.res.SkTreasureBoxConfigListResParam>
     * @author qubianzhong
     * @Date 18:07 2019/8/2
     */
    List<SkTreasureBoxConfigListResParam> list(SkTreasureBoxConfigListReqParam param);

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkTreasureBoxConfigInfoResParam
     * @author qubianzhong
     * @Date 15:45 2019/8/5
     */
    SkTreasureBoxConfigInfoResParam info(Long id);

    /**
     * 获取有效的宝箱配置数量
     *
     * @param
     * @param startTime
     * @param endTime
     * @param id
     * @return int
     * @author qubianzhong
     * @Date 15:57 2019/8/12
     */
    int countOfEffective(@Param("startTime") Date startTime,
                         @Param("endTime") Date endTime,
                         @Param("id") Long id);
}