package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkTopicConfig;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.mapper.SkRankMappingDAO;
import com.geek.shengka.backend.params.req.SkRankMappingListReqParam;
import com.geek.shengka.backend.params.res.SkContentVideoInfoResParam;
import com.geek.shengka.backend.params.res.SkUserInfoResParam;
import com.geek.shengka.backend.service.SkContentCenterService;
import com.geek.shengka.backend.service.SkRankMappingService;
import com.geek.shengka.backend.service.SkTopicConfigService;
import com.geek.shengka.backend.service.SkUserCenterService;
import com.geek.shengka.backend.util.PageDataUtils;
import com.geek.shengka.backend.util.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qubianzhong
 * @date 2019/8/2 10:54
 */
@Service
public class SkRankMappingServiceImpl implements SkRankMappingService {

    @Autowired
    private SkRankMappingDAO skRankMappingDAO;
    @Autowired
    private SkContentCenterService skContentCenterService;
    @Autowired
    private SkTopicConfigService skTopicConfigService;
    @Autowired
    private SkUserCenterService skUserCenterService;

    /**
     * 列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkRankMappingResParam>
     * @author qubianzhong
     * @Date 13:40 2019/8/2
     */
    @Override
    public PageVO<SkContentVideoInfoResParam> videoList(SkRankMappingListReqParam param) throws BaseException {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<String> videoIds = skRankMappingDAO.listIds(param);
        List<SkContentVideoInfoResParam> list = skContentCenterService.getVideoInfosByIds(videoIds);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 话题列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkRankMappingResParam>
     * @author qubianzhong
     * @Date 15:48 2019/8/6
     */
    @Override
    public PageVO<SkTopicConfig> topicList(SkRankMappingListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<String> topicIdsStr = skRankMappingDAO.listIds(param);
        List<SkTopicConfig> list = skTopicConfigService.getInfosByIds(topicIdsStr);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 用户列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkRankMappingResParam>
     * @author qubianzhong
     * @Date 15:49 2019/8/6
     */
    @Override
    public PageVO<SkUserInfoResParam> userList(SkRankMappingListReqParam param) throws BaseException {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<String> userIdsStr = skRankMappingDAO.listIds(param);
        List<SkUserInfoResParam> list = null;
        if (!CollectionUtils.isEmpty(userIdsStr)) {
            List<Long> userIds = userIdsStr.stream().map(Long::valueOf).collect(Collectors.toList());
            list = skUserCenterService.getInfosByIds(userIds);
        }
        return PageDataUtils.pageData(page, list);
    }
}
