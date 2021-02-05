package com.geek.shengka.content.service;

import com.geek.shengka.content.entity.vo.SkSearchModuleVO;
import com.geek.shengka.content.entity.vo.SkSearchSourceVO;
import com.geek.shengka.content.request.SearchRequest;

import java.util.List;

/**
 * @Filename: SkSearchService
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/5 ;
 */
public interface SkSearchService {
    /**
     * 搜索页面配置
     * @return
     * @param channel
     */
    List<SkSearchModuleVO> searchPages(String channel);

    /**
     * 搜索  根据类型搜索  0视频 1用户 2话题
     * @param param
     * @return
     */
    List<SkSearchSourceVO> searchByLabel(SearchRequest param);

    /**
     * 榜单查看更多
     * @param param
     * @return
     */
    List<SkSearchSourceVO> seeMoreSourceList(SearchRequest param);

    /**
     * 人气榜单详情
     * @param param
     * @return
     */
    List<SkSearchSourceVO> popularRankInfo(SearchRequest param);

    /**
     *
     */
    List<SkSearchSourceVO> getManyMedias(List<String> sourceIds);
}
