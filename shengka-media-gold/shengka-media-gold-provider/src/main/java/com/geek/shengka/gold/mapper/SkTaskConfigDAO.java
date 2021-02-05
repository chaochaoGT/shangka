package com.geek.shengka.gold.mapper;

import com.geek.shengka.gold.entity.SkTaskConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkTaskConfigDAO继承基类
 */
@Repository
public interface SkTaskConfigDAO extends MyBatisBaseDao<SkTaskConfig, Long> {

    /**
     * 每日任务列表
     * @return
     */
    List<SkTaskConfig> dailyConfig();

    /**
     * 根据类型查询
     * @param triggerEvent
     * @return
     */
    List<SkTaskConfig> selectByTriggerEvent(Integer triggerEvent);

    /**
     * 全局金币配置
     * @return
     */
    SkTaskConfig globalConfig();
}