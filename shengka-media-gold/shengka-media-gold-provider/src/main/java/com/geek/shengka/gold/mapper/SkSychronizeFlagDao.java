package com.geek.shengka.gold.mapper;

import org.apache.ibatis.annotations.Param;

public interface SkSychronizeFlagDao {

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
