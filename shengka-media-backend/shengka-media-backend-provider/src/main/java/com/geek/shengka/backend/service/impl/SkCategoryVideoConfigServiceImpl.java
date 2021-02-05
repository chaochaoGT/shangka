package com.geek.shengka.backend.service.impl;

import com.geek.shengka.backend.entity.SkCategoryVideoConfig;
import com.geek.shengka.backend.entity.SkPublishVideo;
import com.geek.shengka.backend.mapper.SkCategoryVideoConfigDAO;
import com.geek.shengka.backend.mapper.SkPublishVideoDAO;
import com.geek.shengka.backend.params.req.SkCategoryVideoConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkCategoryVideoConfigListReqParam;
import com.geek.shengka.backend.params.req.SkCategoryVideoConfigUpdateReqParam;
import com.geek.shengka.backend.params.res.SkCategoryVideoConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkCategoryVideoConfigListResParam;
import com.geek.shengka.backend.service.SkCategoryVideoConfigService;
import com.geek.shengka.backend.util.BeanMapperUtils;
import com.geek.shengka.backend.util.CommonUtil;
import com.geek.shengka.backend.util.PageDataUtils;
import com.geek.shengka.backend.util.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/22 13:45
 */
@Service
public class SkCategoryVideoConfigServiceImpl implements SkCategoryVideoConfigService {
    @Autowired
    private SkCategoryVideoConfigDAO skCategoryVideoConfigDAO;
    @Autowired
    private SkPublishVideoDAO skPublishVideoDAO;

    /**
     * 列表
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkCategoryVideoConfigListResParam>
     * @author qubianzhong
     * @Date 13:49 2019/8/22
     */
    @Override
    public PageVO<SkCategoryVideoConfigListResParam> list(SkCategoryVideoConfigListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkCategoryVideoConfigListResParam> list = skCategoryVideoConfigDAO.list();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(config -> config.setVideo(initConfigVideo(config.getVideoId())));
        }
        return PageDataUtils.pageData(page, list);
    }

    private SkPublishVideo initConfigVideo(String videoId) {
        SkPublishVideo video = skPublishVideoDAO.selectByPrimaryKey(videoId);
        if (video != null) {
            video.setVideoUrl(CommonUtil.getFullPath(video.getVideoUrl()));
        }
        return video;
    }

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkCategoryVideoConfigInfoResParam
     * @author qubianzhong
     * @Date 13:51 2019/8/22
     */
    @Override
    public SkCategoryVideoConfigInfoResParam info(Long id) {
        SkCategoryVideoConfigInfoResParam infoResParam = BeanMapperUtils.map(skCategoryVideoConfigDAO.selectByPrimaryKey(id), SkCategoryVideoConfigInfoResParam.class);
        infoResParam.setVideo(initConfigVideo(infoResParam.getVideoId()));
        return infoResParam;
    }

    /**
     * 新增
     *
     * @param addReqParam
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 13:55 2019/8/22
     */
    @Override
    public Boolean add(SkCategoryVideoConfigAddReqParam addReqParam) {
        SkCategoryVideoConfig config = BeanMapperUtils.map(addReqParam, SkCategoryVideoConfig.class);
        config.setCreateTime(new Date());
        return skCategoryVideoConfigDAO.insertSelective(config) > 0;
    }

    /**
     * 更新
     *
     * @param updateReqParam
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 13:55 2019/8/22
     */
    @Override
    public Boolean update(SkCategoryVideoConfigUpdateReqParam updateReqParam) {
        SkCategoryVideoConfig config = BeanMapperUtils.map(updateReqParam, SkCategoryVideoConfig.class);
        config.setUpdateTime(new Date());
        return skCategoryVideoConfigDAO.updateByPrimaryKeySelective(config) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 13:56 2019/8/22
     */
    @Override
    public Boolean delete(Long id) {
        return skCategoryVideoConfigDAO.deleteByPrimaryKey(id) > 0;
    }
}
