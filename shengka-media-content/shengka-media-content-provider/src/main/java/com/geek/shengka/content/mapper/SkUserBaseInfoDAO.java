package com.geek.shengka.content.mapper;

import com.geek.shengka.content.entity.SkUserBaseInfo;
import com.geek.shengka.content.entity.vo.SkSearchSourceVO;
import com.geek.shengka.content.request.SearchRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkUserBaseInfoDAO继承基类
 */
@Repository
public interface SkUserBaseInfoDAO extends MyBatisBaseDao<SkUserBaseInfo, Long> {
    /**
     * 获取用户ids 信息
     * @param createBy
     * @return
     */
    List<SkSearchSourceVO> selectByIds(@Param("userIds") String createBy);

    /**
     * 分页查找匹配用户名
     * @param paraMap
     * @return
     */
    List<SkSearchSourceVO> searchUserByKeyWord(SearchRequest paraMap);
    
    
    int updateUserBaseContentNum(SkUserBaseInfo skUserBaseInfo);
    
    SkUserBaseInfo selectByUser(@Param("userId")long userId);
}