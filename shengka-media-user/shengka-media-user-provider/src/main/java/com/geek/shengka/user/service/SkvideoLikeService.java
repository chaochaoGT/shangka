package com.geek.shengka.user.service;

import com.geek.shengka.common.basemodel.BaseMediaInfo;
import com.geek.shengka.common.basemodel.PageRequest;
import com.geek.shengka.user.entity.vo.SkPublishVideoVO;
import com.geek.shengka.user.request.UserLikePushRequest;

import java.util.List;

/**
 * @Filename: SkvideoLikeService
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/8 ;
 */
public interface SkvideoLikeService {
    /**
     * 当前用户喜欢的作品
     * @param userId
     * @param params
     * @return
     */
    List<BaseMediaInfo> myVideoLikeList(Long userId, PageRequest params);

    /**
     * 当前用户发布的作品
     * @param params
     * @return
     */
    List<SkPublishVideoVO> publishMediaList(Long userId,PageRequest params);

    /**
     * 别人发布的作品
     * @param userId
     * @param params
     * @return
     */
    List<SkPublishVideoVO> otherPublishMediaList(Long userId, UserLikePushRequest params);
}
