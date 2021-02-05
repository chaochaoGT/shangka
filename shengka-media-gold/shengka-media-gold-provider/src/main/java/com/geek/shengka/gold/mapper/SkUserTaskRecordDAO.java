package com.geek.shengka.gold.mapper;

import com.geek.shengka.gold.entity.SkUserTaskRecord;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkUserTaskRecordDAO继承基类
 */
@Repository
public interface SkUserTaskRecordDAO extends MyBatisBaseDao<SkUserTaskRecord, Long> {
    /**
     * 查询用户任务完成记录
     *
     * @param taskId
     * @param userId
     * @return
     */
    List<SkUserTaskRecord> selectTaskRecord(@Param("taskId") Long taskId, @Param("userId") Long userId,@Param("createTimeStart")String createTimeStart,@Param("createTimeEnd")String createTimeEnd);

    /**
     *  获取任务数
     * @param taskId
     * @param userId
     * @param createTimeStart
     * @param createTimeEnd
     * @return
     */
    Integer countByUserIdAndTaskConfigId(@Param("taskId") Long taskId, @Param("userId") Long userId ,@Param("createTimeStart")String createTimeStart,@Param("createTimeEnd")String createTimeEnd);

    /**
     * 根据订单号查询记录
     * @param orderNo
     * @return
     */
    SkUserTaskRecord selectByOrderNo(@Param("orderNo") String orderNo);

    /**
     * 查询个人未领取金币
     * @param taskId
     * @param userId
     * @return
     */
    SkUserTaskRecord selectOneNotReceiveRecord(@Param("taskId") Long taskId, @Param("userId") Long userId,@Param("createTimeStart")String createTimeStart,@Param("createTimeEnd")String createTimeEnd);
    
}