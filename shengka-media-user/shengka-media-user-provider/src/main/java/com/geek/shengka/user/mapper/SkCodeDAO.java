package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkCodeDAO继承基类
 */
@Repository
public interface SkCodeDAO extends MyBatisBaseDao<SkCode, Long> {

    /**
     * 根据codetype查询有效列表
     * @param codeType
     * @return
     */
    public List<SkCode> selectBycodeType(@Param("codeType") String codeType);
}