package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkWithdrawRecord;
import org.springframework.stereotype.Repository;

/**
 * SkWithdrawRecordDAO继承基类
 */
@Repository
public interface SkWithdrawRecordDAO extends MyBatisBaseDao<SkWithdrawRecord, Long> {
}