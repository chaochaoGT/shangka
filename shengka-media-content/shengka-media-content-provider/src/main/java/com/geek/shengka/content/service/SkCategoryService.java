package com.geek.shengka.content.service;

import com.geek.shengka.content.entity.vo.SkCateMediaInfoVO;
import com.geek.shengka.content.request.SkCategoryRequest;

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
}
