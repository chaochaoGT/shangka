package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SklSdsr;
import com.geek.shengka.backend.params.res.SkSdsrListReqParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JrlSdsrMapper继承基类
 */
@Repository
public interface SkSdsrMapper extends MyBatisBaseDao<SklSdsr, Long> {
    /**
     * 查询所有
     *
     * @return
     */
    List<SkSdsrListReqParam> selectAll();

    /**
     * 全部删除
     *
     * @return
     */
    int deleteAll();

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int insertBatch(List<SklSdsr> list);

    /**
     * 清空表
     *
     * @param
     * @return int
     * @author qubianzhong
     * @Date 16:46 2019/8/29
     */
    int truncate();
}