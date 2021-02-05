package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkModProfit;
import com.geek.shengka.backend.params.req.SkModProfitListReqParam;
import com.geek.shengka.backend.params.res.SkModProfitResParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkModProfitDAO extends MyBatisBaseDao<SkModProfit, Long> {
    /**
     * 查询列表
     *
     * @param param
     * @return java.util.List<com.geek.lw.mis.entity.SkModProfit>
     * @author qubianzhong
     * @Date 10:49 2019/8/26
     */
    List<SkModProfitResParam> list(SkModProfitListReqParam param);

    /**
     * 清空表
     *
     * @param
     * @return int
     * @author qubianzhong
     * @Date 11:55 2019/8/29
     */
    int truncate();

    /**
     * 批量插入
     *
     * @param skModProfitList
     * @return int
     * @author qubianzhong
     * @Date 11:56 2019/8/29
     */
    int insertBatch(@Param("list") List<SkModProfit> skModProfitList);
}