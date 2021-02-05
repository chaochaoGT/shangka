package com.geek.shengka.user.mapper;

import com.geek.shengka.user.entity.SkCategory;
import com.geek.shengka.user.entity.SkUserCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SkUserCategoryDAO继承基类
 */
@Repository
public interface SkUserCategoryDAO extends MyBatisBaseDao<SkUserCategory, Long> {
    /**
     * 当前用户已添加的频道
     * @param channel
     * @param userId
     * @return
     */
    List<SkCategory> selectListByUserId(@Param("channel") String channel,@Param("userId") Long userId);

    void deleteByCidUid(@Param("categoryId") Long categoryId,@Param("userId") Long userId);

    void insertSelectives(@Param("collect")List<SkUserCategory> collect);

    void deleteByUid(@Param("userId") Long userId);
}