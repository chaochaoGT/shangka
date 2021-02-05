package com.geek.shengka.backend.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geek.shengka.backend.entity.SkPublishVideo;
import com.geek.shengka.backend.entity.SkTopicConfig;
import com.geek.shengka.backend.entity.SkTopicVideo;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.mapper.SkPublishVideoDAO;
import com.geek.shengka.backend.mapper.SkTopicConfigDAO;
import com.geek.shengka.backend.mapper.SkTopicVideoDAO;
import com.geek.shengka.backend.params.req.SkTopicConfigAddReqParam;
import com.geek.shengka.backend.params.req.SkTopicConfigListReqParam;
import com.geek.shengka.backend.params.req.SkTopicConfigUpdateReqParam;
import com.geek.shengka.backend.params.req.SkTopicVideoAddReqParam;
import com.geek.shengka.backend.params.res.SkTopicConfigInfoResParam;
import com.geek.shengka.backend.params.res.SkTopicConfigListResParam;
import com.geek.shengka.backend.service.SkTopicConfigService;
import com.geek.shengka.backend.util.BeanMapperUtils;
import com.geek.shengka.backend.util.PageDataUtils;
import com.geek.shengka.backend.util.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qubianzhong
 * @date 2019/8/1 17:02
 */
@Service
public class SkTopicConfigServiceImpl implements SkTopicConfigService {

    @Autowired
    private SkTopicConfigDAO skTopicConfigDAO;
    @Autowired
    private SkTopicVideoDAO skTopicVideoDAO;
    @Autowired
    private SkPublishVideoDAO skPublishVideoDAO;


    /**
     * 列表--分页
     *
     * @param param
     * @return com.geek.shengka.backend.util.PageVO<com.geek.shengka.backend.params.res.SkTopicConfigListResParam>
     * @author qubianzhong
     * @Date 17:19 2019/8/1
     */
    @Override
    public PageVO<SkTopicConfigListResParam> list(SkTopicConfigListReqParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<SkTopicConfigListResParam> list = skTopicConfigDAO.list(param);
        return PageDataUtils.pageData(page, list);
    }

    /**
     * 新增
     *
     * @param add
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:22 2019/8/1
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(SkTopicConfigAddReqParam add) throws BaseException {
        SkTopicConfig topicConfig = BeanMapperUtils.map(add, SkTopicConfig.class);
        //校验所关联的视频ID是否是系统导入的视频，是否存在等
        checkTopicVideoIds(add.getTopicVideos());
        int added = skTopicConfigDAO.insertSelective(topicConfig);
        if (added == 0) {
            return false;
        }
        return initTopicVideo(add.getTopicVideos(), topicConfig.getId(), topicConfig.getTopicName());
    }

    private void checkTopicVideoIds(List<SkTopicVideoAddReqParam> topicVideos) throws BaseException {
        if (CollectionUtils.isEmpty(topicVideos)) {
            return;
        }
        List<String> videoIds = topicVideos.stream().map(SkTopicVideoAddReqParam::getVideoId).collect(Collectors.toList());
        List<String> pgcVideoIds = skPublishVideoDAO.selectPgcVideoIds(videoIds);
        if (pgcVideoIds == null) {
            throw new BaseException("视频IDS:" + JSONObject.toJSONString(videoIds) + "非法，必须为系统导入视频的IDS！");
        }
        if (videoIds.size() != pgcVideoIds.size()) {
            videoIds = videoIds.stream().filter(id -> !pgcVideoIds.contains(id)).collect(Collectors.toList());
            throw new BaseException("视频IDS:" + JSONObject.toJSONString(videoIds) + "非法，必须为系统导入视频的IDS！");
        }
    }

    private Boolean initTopicVideo(List<SkTopicVideoAddReqParam> topicVideos, Long topicConfigId, String topicName) {
        if (CollectionUtils.isEmpty(topicVideos)) {
            return true;
        }
        //对应视频需要更新的话题信息
        JSONObject topic = new JSONObject();
        topic.put("id", topicConfigId);
        topic.put("topicName", topicName);
        //初始化话题ID，更新视频中的话题冗余字段
        topicVideos.forEach(tv -> {
            //初始化话题配置的ID
            tv.setTopicId(topicConfigId);
            //同步更新视频对应的话题JSON中的数据
            updatePublishVideo(tv, topic, topicName);
        });
        //将话题与视频的对应关系插入数据库
        List<SkTopicVideo> topicVideoList = BeanMapperUtils.mapList(topicVideos, SkTopicVideo.class);
        return skTopicVideoDAO.insertSelectiveList(topicVideoList) > 0;
    }

    private void updatePublishVideo(SkTopicVideoAddReqParam tv, JSONObject topic, String topicName) {
        SkPublishVideo skPublishVideo = skPublishVideoDAO.selectByPrimaryKey(tv.getVideoId());
        String topicJson = skPublishVideo.getTopicJson();
        JSONArray topicJsonArray = new JSONArray();
        if (!StringUtils.isEmpty(topicJson)) {
            topicJsonArray = JSONArray.parseArray(topicJson);
        }
        //如果之前存在，则进行替换
        int size = topicJsonArray.size();
        JSONObject element;
        boolean isExist = false;
        for (int i = 0; i < size; i++) {
            element = topicJsonArray.getJSONObject(i);
            if (element.getLongValue("id") == tv.getTopicId()) {
                topicJsonArray.set(i, topic);
                isExist = true;
            }
        }
        //如果之前不存在，则进行添加
        if (!isExist) {
            topicJsonArray.add(topic);
        }
        skPublishVideo = new SkPublishVideo();
        skPublishVideo.setId(tv.getVideoId());
        skPublishVideo.setTopicJson(topicJsonArray.toJSONString());
        skPublishVideoDAO.updateByPrimaryKeySelective(skPublishVideo);
    }

    /**
     * 更新
     *
     * @param update
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:22 2019/8/1
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(SkTopicConfigUpdateReqParam update) throws BaseException {
        SkTopicConfig topicConfig = BeanMapperUtils.map(update, SkTopicConfig.class);
        //校验所关联的视频ID是否是系统导入的视频，是否存在等
        checkTopicVideoIds(update.getTopicVideos());
        int updated = skTopicConfigDAO.updateByPrimaryKeySelective(topicConfig);
        if (updated == 0) {
            return false;
        }
        if (!CollectionUtils.isEmpty(update.getTopicVideos())) {
            skTopicVideoDAO.deleteByTopicId(topicConfig.getId());
        }
        return initTopicVideo(update.getTopicVideos(), topicConfig.getId(), topicConfig.getTopicName());
    }

    /**
     * 删除
     *
     * @param id
     * @return java.lang.Boolean
     * @author qubianzhong
     * @Date 17:22 2019/8/1
     */
    @Override
    public Boolean delete(Long id) throws BaseException {
        int count = skTopicVideoDAO.countByTopicId(id);
        if (count > 0) {
            throw new BaseException("此话题已关联视频，不允许删除！可以尝试开关状态。");
        }
        return skTopicConfigDAO.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 根据话题名称查询数量
     *
     * @param topicName
     * @return int
     * @author qubianzhong
     * @Date 18:16 2019/8/1
     */
    @Override
    public Long countByTopicName(String topicName) {
        return skTopicConfigDAO.countByTopicName(topicName);
    }

    /**
     * 详情
     *
     * @param id
     * @return com.geek.shengka.backend.params.res.SkTopicConfigInfoResParam
     * @author qubianzhong
     * @Date 15:15 2019/8/5
     */
    @Override
    public SkTopicConfigInfoResParam info(Long id) {
        return skTopicConfigDAO.info(id);
    }

    /**
     * 通过ID来查询列表
     *
     * @param sourceIdStrList
     * @return java.util.List<com.geek.shengka.backend.entity.SkTopicConfig>
     * @author qubianzhong
     * @Date 14:33 2019/8/7
     */
    @Override
    public List<SkTopicConfig> getInfosByIds(List<String> sourceIdStrList) {
        List<SkTopicConfig> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sourceIdStrList)) {
            List<Long> ids = sourceIdStrList.stream().map(Long::valueOf).collect(Collectors.toList());
            list = skTopicConfigDAO.getInfosByIds(ids);
        }
        return list;
    }

    /**
     * 校验话题IDS
     *
     * @param ids
     * @return void
     * @author qubianzhong
     * @Date 16:19 2019/8/13
     */
    @Override
    public void checkTopicIds(List<String> ids) throws BaseException {
        List<SkTopicConfig> topicConfigs = getInfosByIds(ids);
        if (CollectionUtils.isEmpty(topicConfigs)) {
            throw new BaseException("话题ID不存在:" + ids);
        }
        List<Long> notExistIds = topicConfigs.stream().map(SkTopicConfig::getId).filter(id -> !ids.contains(String.valueOf(id))).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(notExistIds)) {
            throw new BaseException("话题ID不存在:" + notExistIds);
        }
    }
}
