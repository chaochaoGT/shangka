package com.geek.shengka.gold.mapper;

import com.geek.shengka.gold.entity.SkTreasureBoxConfig;
import org.springframework.stereotype.Repository;

/**
 * SkTreasureBoxConfigDAO继承基类
 */
@Repository
public interface SkTreasureBoxConfigDAO extends MyBatisBaseDao<SkTreasureBoxConfig, Long> {

    /**
     * 获取当前可用的宝箱配置
     * @return
     */
    SkTreasureBoxConfig selectConfig();
}