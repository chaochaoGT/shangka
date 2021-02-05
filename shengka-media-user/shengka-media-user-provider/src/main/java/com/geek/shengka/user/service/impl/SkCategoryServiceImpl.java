package com.geek.shengka.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.geek.shengka.common.basemodel.BaseMediaInfo;
import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.common.proxy.ContentVideoInfoProxy;
import com.geek.shengka.common.request.BaseContentRequest;
import com.geek.shengka.common.util.CdnUrlUtils;
import com.geek.shengka.user.constant.UserConstant;
import com.geek.shengka.user.entity.*;
import com.geek.shengka.user.entity.vo.CategoryTopMediaUpVO;
import com.geek.shengka.user.entity.vo.SkCateMediaInfoVO;
import com.geek.shengka.user.entity.vo.SkRecommendUserConfigVO;
import com.geek.shengka.user.entity.vo.SuperVO;
import com.geek.shengka.user.mapper.*;
import com.geek.shengka.user.request.SkCategoryRequest;
import com.geek.shengka.user.service.SkCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Filename: SkCategoryServiceImpl
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/7/31 ;
 */
@Service
public class SkCategoryServiceImpl implements SkCategoryService {

    private Logger logger= LoggerFactory.getLogger(SkCategoryServiceImpl.class);

    @Autowired
    private SkCategoryVideoConfigDAO categoryVideoConfigDAO;

    @Autowired
    private SkRecommendUserConfigDAO recommendUserConfigDAO;

    @Autowired
    private SkCategoryDAO skCategoryDAO;

    @Autowired
    private SkUserCategoryDAO skUserCategoryDAO;

    @Autowired
    private SkCategoryMappingDAO skCategoryMappingDAO;

    @Autowired
    private SkTopicVideoDAO skTopicVideoDAO;

    @Autowired
    private SkVideoLikeDAO skVideoLikeDAO;

    @Autowired
    private SkPublishVideoDAO skPublishVideoDAO;

    @Autowired
    private SkUserBaseInfoDAO skUserBaseInfoDAO;
    @Autowired
    private SkFansDAO skFansDAO;

    @Autowired
    private ContentVideoInfoProxy contentVideoInfoProxy;

    @Override
    public CategoryTopMediaUpVO topMediaUpList(SkCategoryRequest request) {
        CategoryTopMediaUpVO vo = new CategoryTopMediaUpVO();
        try {
            //顶部视频默认6个
            List<BaseMediaInfo> collect1 = topMediaList(request);
            //up人员信息
            List<SkRecommendUserConfigVO> userConfigs = topUpList(request);
            vo.setTopMedia(collect1);
            vo.setUpList(userConfigs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return vo;
    }

    @Override
    public SuperVO categoryRecList(String channel, Long userId) {
        try {
            SuperVO vo=new SuperVO();
            //自己
            List<SkCategory> myCategory= myCategoryList("",userId);
            //将要添加
            List<SkCategory> sysCategory= willAddCategoryList("",userId);
            vo.put("myCategory",myCategory);
            vo.put("willAddCategory",sysCategory);
            return vo;
        } catch (Exception e) {
            logger.error("categoryRecList is error");
            return new SuperVO();
        }
    }

    @Override
    public void delMyCategory(Long categoryId, Long userId) {
        skUserCategoryDAO.deleteByCidUid(categoryId,userId);
    }

    @Override
    public void addMyCategory(String categoryId, Long userId, String channel) {
        if (userId<=0){
            logger.info("当前请求categoryId为空 categoryId={} 或当前用户未登录userId={}",categoryId,userId);
            return;
        }
        //delete-all
        skUserCategoryDAO.deleteByUid(userId);
        if (StringUtils.isEmpty(categoryId)){
            return;
        }
        //当前渠道可添加的catergory
        List<SkCategory> list = willAddCategoryList(channel, -1L);
        List<String> collect1 = list.stream().map(c -> { return String.valueOf(c.getId());}).collect(Collectors.toList());
        List<String> cids = Arrays.asList(categoryId.split(","));
        List<SkUserCategory> collect =new ArrayList<>(5);
        for (int i = 0; i < cids.size(); i++) {
            //排除失效频道或系统cids,只添加cid为可选的
            if (!collect1.contains(cids.get(i))){
                logger.warn("当前频道失效 或 为系统频道！！！cid={}",cids.get(i));
                continue;
            }
            SkUserCategory userCategory = SkUserCategory.builder()
                    .categoryId(Long.valueOf(cids.get(i)))
                    .userId(userId)
                    .createTime(new Date())
                    .updateTime(new Date())
                    .seq(i+1)
                    .build();
            collect.add(userCategory);
        }
        if (!CollectionUtils.isEmpty(collect)){
            skUserCategoryDAO.insertSelectives(collect);
        }
    }

    @Override
    public List<SkCateMediaInfoVO> mediaListByCategoryId(SkCategoryRequest request) {
        //内容中心CategoryIds
        List<String> contIds = skCategoryMappingDAO.selectByParams(request.getCategoryId());
        if (CollectionUtils.isEmpty(contIds)) {
            logger.debug("当前CategoryId={} 未配置内容中心contCategoryId={}", request.getCategoryId(), contIds.size());
            return new ArrayList<>();
        }
        //短视频
        BaseContentRequest contentRequest = BaseContentRequest.builder().watchMode(UserConstant.WATCH_MODE_0)
                .pageNo(request.getPageIndex())
                .pageSize(request.getPageCount())
                .categoryCodes(contIds).build();
        List<BaseMediaInfo> homeRecommend = contentVideoInfoProxy.multiConditionGetMedias(contentRequest);
        List<SkCateMediaInfoVO> result = null;

        try {
            if (!CollectionUtils.isEmpty(homeRecommend)) {
                result = transFromBaseMediaInfo(homeRecommend);
                Map<String, SkCateMediaInfoVO> vidsVinfo =
                        result.stream().collect(Collectors.toMap(SkCateMediaInfoVO::getId, tv -> tv));
                //1.1 视频ids
                List<String> vids = result.stream().map(SkCateMediaInfoVO::getId).collect(Collectors.toList());
                //1 话题和@好友
                List<SkPublishVideo> skPublishVideos = skPublishVideoDAO.selectPublishInfosByVids(vids);
                if (!CollectionUtils.isEmpty(skPublishVideos)) {

                    //数据封装
                    skPublishVideos.forEach(tv -> {
                        String noticeUserJson = tv.getNoticeUserJson();
                        String topicJson = tv.getTopicJson();
                        SkCateMediaInfoVO skCateMediaInfoVO = vidsVinfo.get(tv.getId());
                        //@好友
                        if (StringUtils.isNotEmpty(noticeUserJson)) {
                            List<SkUserBaseInfo> skUserBaseInfos = JSON.parseObject(noticeUserJson,
                                    new TypeReference<List<SkUserBaseInfo>>() {
                                    });
                            skCateMediaInfoVO.setNoticeUserinfo(skUserBaseInfos);
                        }
                        //话题
                        if (StringUtils.isNotEmpty(topicJson)) {
                            List<SkTopicConfig> skTopicConfigs = JSON.parseObject(topicJson,
                                    new TypeReference<List<SkTopicConfig>>() {
                                    });
                            skCateMediaInfoVO.setTopicInfo(skTopicConfigs);
                        }
                    });
                }
                //2登录用户 喜欢 关注
                if (null != request.getUserId() && request.getUserId() >=0 ) {
                    //喜欢
                    List<String> likeVideos= skVideoLikeDAO.myLikeVideos(request.getUserId(),vids);
                    if (!CollectionUtils.isEmpty(likeVideos)){
                        likeVideos.stream().filter(lv -> null != vidsVinfo.get(lv)).map(v -> {
                            SkCateMediaInfoVO skCateMediaInfoVO = vidsVinfo.get(v);
                            skCateMediaInfoVO.setLikeFlag(UserConstant.subscrip_status_1);
                            return skCateMediaInfoVO;
                        }).collect(Collectors.toList());
                    }
                    //关注
                    Map<String, SkCateMediaInfoVO> authorIdsVinfo =
                            result.stream().collect(Collectors.toMap(SkCateMediaInfoVO::getAuthorId, Function.identity(), (v,replacement) -> v));
                    List<String> fansUserIds= skFansDAO.selectMyFans(request.getUserId(),authorIdsVinfo.keySet());
                    if (!CollectionUtils.isEmpty(fansUserIds)) {
                        fansUserIds.stream().filter(lv -> null != authorIdsVinfo.get(lv)).map(v -> {
                            SkCateMediaInfoVO skCateMediaInfoVO = authorIdsVinfo.get(v);
                            skCateMediaInfoVO.setAttentionFlag(UserConstant.subscrip_status_1);
                            return skCateMediaInfoVO;
                        }).collect(Collectors.toList());
                    }
                }
                return result;
            } else {
                logger.info("请求内容中心 返回视频为空 multiConditionGetMedias  request={} skcontIds.size={}", JSON.toJSONString(request),JSON.toJSONString(contIds));

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private List<SkCateMediaInfoVO> transFromBaseMediaInfo(List<BaseMediaInfo> homeRecommend) {
        List<SkCateMediaInfoVO> collect = homeRecommend.stream().map(h -> {
            SkCateMediaInfoVO vo = new SkCateMediaInfoVO();
            vo.setId(h.getId());
            vo.setUrl(CdnUrlUtils.transferCdn(h.getUrl()));
            vo.setTitle(h.getTitle());
            vo.setCoverImage(CdnUrlUtils.transferCdn(h.getCoverImage()));
            vo.setWatchMode(h.getWatchMode());
            vo.setContentCategoryCode(h.getContentCategoryCode());
            vo.setContentCategoryName(h.getContentCategoryName());
            vo.setDuration(h.getDuration());
            vo.setCommentNums(h.getCommentNums());
            vo.setGiveThumbsNums(h.getGiveThumbsNums());
            vo.setHasBeenCollected(h.getHasBeenCollected());
            vo.setWatchedTimes(h.getWatchedTimes());
            vo.setSize(h.getSize());
            vo.setScore(h.getScore());
            vo.setAuthorId(h.getAuthorId());
            vo.setAvatar(h.getAvatar());
            vo.setNickname(h.getNickname());
            vo.setIndexId(h.getIndexId());
            return vo;
        }).collect(Collectors.toList());

        return collect;
    }

    @Override
    public boolean checkCategoryByChannel(Long categoryId, String channel) {
        SkCategory cc= skCategoryDAO.selectByParams(categoryId,"");
        if (null!=cc){
            return true;
        }
        return false;
    }

    @Override
    public List<SkCategory> myCategoryList(String channel, Long userId) {
        List<SkCategory> defaultCategory = getDefaultCategory(channel);
        if (userId>0){
            List<SkCategory> skCategories = skUserCategoryDAO.selectListByUserId("", userId);
            defaultCategory.addAll(skCategories);
        }
        return defaultCategory;
    }

    @Override
    public List<SkCategory> getDefaultCategory(String channel) {
        //缓存获取
        String key = String.format(UserConstant.DEFAULT_CATEGORYS_CHANNEL, channel);
        List<SkCategory> skCategories= CacheProvider.getObject(key,List.class);
        if (CollectionUtils.isEmpty(skCategories)){
            Map<String,Object> paraMap=new HashMap<>(5);
            //paraMap.put("channel",channel);
            paraMap.put("categoryType", UserConstant.CATEGORYS_TYPE_1);
            skCategories = skCategoryDAO.categoryList(paraMap);
            CacheProvider.setObject(key,skCategories, UserConstant.REDIS_EXIST_TIME);
        }
        return skCategories;
    }

    @Override
    public List<SkCategory> willAddCategoryList(String channel,Long userId) {
        //系统
        Map<String,Object> paraMap=new HashMap<>(5);
        //paraMap.put("channel",channel);
        paraMap.put("categoryType", UserConstant.CATEGORYS_TYPE_2);
        List<SkCategory> skCategories = skCategoryDAO.categoryList(paraMap);
        //已登陆
        if (userId>0){
            //排除已添加的category
            List<SkCategory> myCategory= skUserCategoryDAO.selectListByUserId("",userId);
            List<Long> collect = myCategory.stream().map(SkCategory::getId).collect(Collectors.toList());
            skCategories =
                    skCategories.stream().filter(c -> !collect.contains(c.getId())).collect(Collectors.toList());
            paraMap.put("ids",collect);

        }
        return skCategories;
    }

    @Override
    public List<BaseMediaInfo> topMediaList(SkCategoryRequest request) {
        List<SkCategoryVideoConfig> videoConfigs =
                categoryVideoConfigDAO.getTopMediaList(request.getStartRecordNumb(), request.getPageCount());
        if (CollectionUtils.isEmpty(videoConfigs)) {
            logger.info("topMediaList is empty");
            return null;
        }
        List<String> vids = videoConfigs.stream().map(SkCategoryVideoConfig::getVideoId).collect(Collectors.toList());
        //内容中心
        logger.info("params={}", JSON.toJSONString(request));
        BaseContentRequest build =
                BaseContentRequest.builder().videoIds(vids).pageNo(request.getPageIndex()).pageSize(vids.size()).build();
        List<BaseMediaInfo> baseMediaInfos = contentVideoInfoProxy.multiConditionGetMedias(build);
        if (CollectionUtils.isEmpty(baseMediaInfos)) {
            logger.info("请求内容中心 返回视频为空 multiConditionGetMedias  request={} skvids.size={}",
                    JSON.toJSONString(build), vids.size());
            return new ArrayList<>(1);
        }
        Map<String, BaseMediaInfo> collect =
                baseMediaInfos.stream().collect(Collectors.toMap(BaseMediaInfo::getId, mediaInfo -> mediaInfo));

        List<BaseMediaInfo> collect1 = vids.stream().filter(a -> !Objects.isNull(collect.get(a))).map(b -> {
            BaseMediaInfo mediaInfo = collect.get(b);
            mediaInfo.setUrl(CdnUrlUtils.transferCdn(mediaInfo.getUrl()));
            mediaInfo.setCoverImage(CdnUrlUtils.transferCdn(mediaInfo.getCoverImage()));
            return mediaInfo;
        }).collect(Collectors.toList());
        return collect1;
    }

    @Override
    public List<SkRecommendUserConfigVO> topUpList(SkCategoryRequest request) {
        return  recommendUserConfigDAO.getUpList(request.getUserId(),request.getStartRecordNumb(),request.getPageCount());
    }
}
