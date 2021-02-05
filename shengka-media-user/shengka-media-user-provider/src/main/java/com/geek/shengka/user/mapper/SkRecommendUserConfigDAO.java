package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkRecommendUserConfig;
import com.geek.shengka.user.entity.vo.SkRecommendUserConfigVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkRecommendUserConfigDAO继承基类
 */
@Repository
public interface SkRecommendUserConfigDAO extends MyBatisBaseDao<SkRecommendUserConfig, Long> {
    /**
     * 分页 运营账户
     * @param startRecordNumb
     * @param pageCount
     * @return
     */
    List<SkRecommendUserConfigVO> getUpList(@Param("userId")Long userId, @Param("startRecordNumb") int startRecordNumb, @Param("pageCount") int pageCount);
}