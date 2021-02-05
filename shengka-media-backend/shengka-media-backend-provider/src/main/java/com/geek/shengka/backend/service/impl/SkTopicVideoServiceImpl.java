package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.mapper.SkTopicVideoDAO;
import com.geek.shengka.backend.params.req.SkTopicVideoListReqParam;
import com.geek.shengka.backend.params.res.SkContentVideoInfoResParam;
import com.geek.shengka.backend.service.SkContentCenterService;
import com.geek.shengka.backend.service.SkTopicVideoService;
import com.geek.shengka.backend.util.PageDataUtils;
import com.geek.shengka.backend.util.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/1 19:51
 */
@Service
public class SkTopicVideoServiceImpl implements SkTopicVideoService {
    @Autowired
    private SkTopicVideoDAO skTopicVideoDAO;
    @Autowired
    private SkContentCenterService skContentCenterService;

    /**
     * 根据topicId分页查询
     *
     * @param reqParam
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkTopicVideoListResParam>
     * @author qubianzhong
     * @Date 20:38 2019/8/1
     */
    @Override
    public PageVO<SkContentVideoInfoResParam> list(SkTopicVideoListReqParam reqParam) throws BaseException {
        Page page = PageHelper.startPage(reqParam.getPageNo(), reqParam.getPageSize());
        List<String> videoIds = skTopicVideoDAO.listVideoIds(reqParam);
        List<SkContentVideoInfoResParam> infoList = skContentCenterService.getVideoInfosByIds(videoIds);
        return PageDataUtils.pageData(page, infoList);
    }
}
