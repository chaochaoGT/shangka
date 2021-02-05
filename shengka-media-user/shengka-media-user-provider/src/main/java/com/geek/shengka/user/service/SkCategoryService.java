package com.geek.shengka.user.service;

import com.geek.shengka.common.basemodel.BaseMediaInfo;
import com.geek.shengka.user.entity.SkCategory;
import com.geek.shengka.user.entity.vo.CategoryTopMediaUpVO;
import com.geek.shengka.user.entity.vo.SkCateMediaInfoVO;
import com.geek.shengka.user.entity.vo.SkRecommendUserConfigVO;
import com.geek.shengka.user.entity.vo.SuperVO;
import com.geek.shengka.user.request.SkCategoryRequest;

import java.util.List;

/**
 * @Filename: SkCategoryService
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/7/31 ;
 */

public interface SkCategoryService {


    /**
     *频道页顶部视频、up主列表
     * @param request
     * @return
     */
    CategoryTopMediaUpVO topMediaUpList(SkCategoryRequest request);

    /**
     *频道推荐（我的频道-待添加频道）
     * @param channel
     * @param userId
     * @return
     */
    SuperVO categoryRecList(String channel, Long userId);

    /**
     * 删除我的频道
     * @param categoryId
     * @param userId
     */
    void delMyCategory(Long categoryId, Long userId);

    /**
     * 添加我的频道
     * @param categoryId
     * @param userId
     * @param channel
     */
    void addMyCategory(String categoryId, Long userId, String channel);

    /**
     * 根据频道获取视频
     * @param request
     * @return
     */
    List<SkCateMediaInfoVO> mediaListByCategoryId(SkCategoryRequest request);

    /**
     * 根据渠道校验频道是否有效
     * @param categoryId
     * @param channel
     * @return
     */
    boolean checkCategoryByChannel(Long categoryId, String channel);

    /**
     * 我的频道
     * @param channel
     * @param userId
     * @return
     */
    List<SkCategory> myCategoryList(String channel, Long userId);
    /**
     * 获取默认频道
     * @param channel
     * @return
     */
    List<SkCategory> getDefaultCategory(String channel);

    /**
     * 获取待添加频道
     * @param channel
     * @param userId
     * @return
     */
    List<SkCategory> willAddCategoryList(String channel,Long userId);

    List<BaseMediaInfo> topMediaList(SkCategoryRequest request);

    List<SkRecommendUserConfigVO> topUpList(SkCategoryRequest request);
}
