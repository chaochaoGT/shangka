package com.geek.shengka.gold.mapper;

import com.geek.shengka.gold.entity.SkUserTreasureBoxRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkUserTreasureBoxRecordDAO继承基类
 */
@Repository
public interface SkUserTreasureBoxRecordDAO extends MyBatisBaseDao<SkUserTreasureBoxRecord, Long> {

    /**
     * 获取当天用户的宝箱记录
     *
     * @param userId
     * @param createTimeStart
     * @param createTimeEnd
     * @param state
     * @return
     */
    List<SkUserTreasureBoxRecord> selectUserRecordByDay(@Param("userId") Long userId, @Param("createTimeStart") String createTimeStart, @Param("createTimeEnd") String createTimeEnd, @Param("state") String state);

    /**
     * 根据订单号查询记录
     *
     * @param orderNo
     * @return
     */
    SkUserTreasureBoxRecord selectByOrderNo(@Param("orderNo") String orderNo);
}