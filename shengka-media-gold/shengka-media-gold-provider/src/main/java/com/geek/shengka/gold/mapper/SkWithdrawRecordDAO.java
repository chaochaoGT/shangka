package com.geek.shengka.gold.mapper;

import com.geek.shengka.gold.entity.SkWithdrawRecord;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * SkWithdrawRecordDAO继承基类
 */
@Repository
public interface SkWithdrawRecordDAO extends MyBatisBaseDao<SkWithdrawRecord, Long>  {
	
    List<SkWithdrawRecord>  selectByHandleState();
    
    
    int updateByOrderNo(SkWithdrawRecord record);
    
    //并发加锁处理
    int attachDataLock(@Param("scychronizeFlag") String scychronizeFlag, @Param("id") Long id);
    //已结算
    int processFinish(@Param("id") Long id);
    //释放抢占锁
    int releaseDataLock(@Param("scychronizeFlag") String scychronizeFlag, @Param("id") Long id);
    //查询是否抢占成功
    Long selectByDataLock(@Param("scychronizeFlag") String scychronizeFlag, @Param("id") Long id);
 
    
    /**
     * 服务退出，并发抢占位兜底处理
     * @param scychronizeFlag
     */
    void handleFinal(@Param("scychronizeFlag") String scychronizeFlag);
    
    
    
    
}

