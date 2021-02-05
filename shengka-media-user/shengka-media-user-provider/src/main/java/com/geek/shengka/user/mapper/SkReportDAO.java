package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkReport;
import org.springframework.stereotype.Repository;

/**
 * SkReportDAO继承基类
 */
@Repository
public interface SkReportDAO extends MyBatisBaseDao<SkReport, Long> {
}