package com.geek.shengka.backend.mapper;

import com.geek.shengka.backend.entity.SkspScCited;
import com.geek.shengka.backend.params.req.SkspScCitedQueryDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkspScCitedDAO extends MyBatisBaseDao<SkspScCited,Long> {

    List<SkspScCited> getList(SkspScCitedQueryDTO param);

    Boolean insertBatch(@Param("list") List<SkspScCited> scCitedList);

    int truncate();
}