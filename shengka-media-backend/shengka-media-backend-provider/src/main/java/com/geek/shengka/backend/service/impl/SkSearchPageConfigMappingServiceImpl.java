package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkTopicConfig;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.mapper.SkSearchModuleMappingDAO;
import com.geek.shengka.backend.mapper.SkTopicConfigDAO;
import com.geek.shengka.backend.params.req.SkSearchPageConfigMappingListReqParam;
import com.geek.shengka.backend.params.res.SkContentVideoInfoResParam;
import com.geek.shengka.backend.service.SkContentCenterService;
import com.geek.shengka.backend.service.SkSearchPageConfigMappingService;
import com.geek.shengka.backend.service.SkTopicConfigService;
import com.geek.shengka.backend.util.PageDataUtils;
import com.geek.shengka.backend.util.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/7 14:18
 */
@Service
public class SkSearchPageConfigMappingServiceImpl implements SkSearchPageConfigMappingService {


    @Autowired
    private SkSearchModuleMappingDAO skSearchModuleMappingDAO;
    @Autowired
    private SkContentCenterService skContentCenterService;
    @Autowired
    private SkTopicConfigDAO skTopicConfigDAO;
    @Autowired
    private SkTopicConfigService skTopicConfigService;

    /**
     * 视频列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkContentVideoInfoResParam>
     * @author qubianzhong
     * @Date 14:23 2019/8/7
     */
    @Override
    public PageVO<SkContentVideoInfoResParam> videoList(SkSearchPageConfigMappingListReqParam param) throws BaseException {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<String> sourceIdStrList = skSearchModuleMappingDAO.listIds(param);
        List<SkContentVideoInfoResParam> list = null;
        if (!CollectionUtils.isEmpty(sourceIdStrList)) {
            list = skContentCenterService.getVideoInfosByIds(sourceIdStrList);
        }
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 话题列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.entity.SkTopicConfig>
     * @author qubianzhong
     * @Date 14:23 2019/8/7
     */
    @Override
    public PageVO<SkTopicConfig> topicList(SkSearchPageConfigMappingListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<String> sourceIdStrList = skSearchModuleMappingDAO.listIds(param);
        List<SkTopicConfig> list = skTopicConfigService.getInfosByIds(sourceIdStrList);
        return PageDataUtils.pageData(page, list);
    }
}
