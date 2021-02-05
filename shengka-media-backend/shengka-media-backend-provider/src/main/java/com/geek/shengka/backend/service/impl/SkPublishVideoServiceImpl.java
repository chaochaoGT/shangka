package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.mapper.SkPublishVideoDAO;
import com.geek.shengka.backend.params.req.SkPublishVideoListReqParam;
import com.geek.shengka.backend.params.res.SkPublishVideoListResParam;
import com.geek.shengka.backend.service.SkPublishVideoService;
import com.geek.shengka.backend.util.CommonUtil;
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
 * @date 2019/8/20 15:29
 */
@Service
public class SkPublishVideoServiceImpl implements SkPublishVideoService {
    @Autowired
    private SkPublishVideoDAO skPublishVideoDAO;

    /**
     * 根据视频标题进行分页查询
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkPublishVideoListResParam>
     * @author qubianzhong
     * @Date 15:51 2019/8/20
     */
    @Override
    public PageVO<SkPublishVideoListResParam> list(SkPublishVideoListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkPublishVideoListResParam> list = skPublishVideoDAO.list(param);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(element -> {
                element.setCoverImageUrl(CommonUtil.getFullPath(element.getCoverImageUrl()));
                element.setVideoUrl(CommonUtil.getFullPath(element.getVideoUrl()));
            });
        }
        return PageDataUtils.pageData(page, list);
    }
}
