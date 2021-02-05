package com.geek.shengka.content.service.impl;

import com.alibaba.fastjson.JSON;
import com.geek.shengka.common.basemodel.BaseMediaInfo;
import com.geek.shengka.common.http.ContextTools;
import com.geek.shengka.common.util.CdnUrlUtils;
import com.geek.shengka.content.config.RecommendConfig;
import com.geek.shengka.content.constans.CategoryConstans;
import com.geek.shengka.content.entity.SkCategory;
import com.geek.shengka.content.entity.vo.SkCateMediaInfoVO;
import com.geek.shengka.content.mapper.*;
import com.geek.shengka.content.request.SkCategoryRequest;
import com.geek.shengka.content.service.SkCategoryService;
import com.geek.shengka.content.service.recommand.RecommandService;
import com.geek.shengka.content.service.recommand.entity.ShortVideoRecommandParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    private SkCategoryDAO skCategoryDAO;

    @Autowired
    private SkCategoryMappingDAO skCategoryMappingDAO;

    @Autowired
    private SkPublishVideoDAO skPublishVideoDAO;

    @Autowired
    private SkFansDAO skFansDAO;

    @Autowired
    private SkVideoLikeDAO skVideoLikeDAO;

    @Autowired
    private RecommandService recommandService;

    @Autowired
    private RecommendConfig recommendConfig;

    @Override
    public List<SkCateMediaInfoVO> mediaListByCategoryId(SkCategoryRequest request) {
        //内容中心CategoryIds
//        List<String> contIds = skCategoryMappingDAO.selectByParams(request.getCategoryId());
//        if (CollectionUtils.isEmpty(contIds)) {
//            logger.debug("当前CategoryId={} 未配置内容中心contCategoryId={}", request.getCategoryId(), contIds.size());
//            return new ArrayList<>();
//        }
        //短视频
        ShortVideoRecommandParam shortVideoRecommandParam=new ShortVideoRecommandParam();
        shortVideoRecommandParam.setImei(ContextTools.getRequest().getHeader("imei"));
        shortVideoRecommandParam.setCategoryId(request.getCategoryId());
        shortVideoRecommandParam.setPageSize(recommendConfig.getDefaultPageSize());
        List<BaseMediaInfo> homeRecommend = recommandService.recommandShortVideos(shortVideoRecommandParam);

        List<SkCateMediaInfoVO> result = null;
        try {

            if (!CollectionUtils.isEmpty(homeRecommend)) {
                result = transFromBaseMediaInfo(homeRecommend);
                Map<String, SkCateMediaInfoVO> vidsVinfo =
                        result.stream().collect(Collectors.toMap(SkCateMediaInfoVO::getId, tv -> tv));
                //1.1 视频ids
                List<String> vids = result.stream().map(SkCateMediaInfoVO::getId).collect(Collectors.toList());
                //1 话题和@好友
                List<SkCateMediaInfoVO> skPublishVideos = skPublishVideoDAO.selectPublishInfosByVids(vids,0L);

                if (!CollectionUtils.isEmpty(skPublishVideos)) {
                    //数据封装
                    skPublishVideos.forEach(tv -> {
                        SkCateMediaInfoVO skCateMediaInfoVO = vidsVinfo.get(tv.getId());
                        skCateMediaInfoVO.setNoticeUserinfo(tv.getNoticeUserinfo());
                        skCateMediaInfoVO.setTopicInfo(tv.getTopicInfo());
                    });
                }
                //2登录用户 喜欢 关注
                if (null != request.getUserId() && request.getUserId() >=0 ) {
                    //喜欢
                    List<String> likeVideos = skVideoLikeDAO.myVideoLikeList(vids, request.getUserId());
                    if (!CollectionUtils.isEmpty(likeVideos)) {
                        likeVideos.stream().filter(lv -> null != vidsVinfo.get(lv)).map(v -> {
                            SkCateMediaInfoVO skCateMediaInfoVO = vidsVinfo.get(v);
                            skCateMediaInfoVO.setLikeFlag(CategoryConstans.Like_Flag_1);
                            return skCateMediaInfoVO;
                        }).collect(Collectors.toList());
                    }

                    //关注
                    Set<String> authorIds =
                            result.stream().map(SkCateMediaInfoVO::getAuthorId).collect(Collectors.toSet());
                    List<String> fansUserIds = skFansDAO.selectMyFans(request.getUserId(), authorIds);
                    if (!CollectionUtils.isEmpty(fansUserIds)) {
                        result.stream().filter(r->fansUserIds.contains(r.getAuthorId())).map(r->{
                            r.setAttentionFlag(CategoryConstans.Attention_Flag_1);
                            return r;
                        }).collect(Collectors.toList());
                    }
                }
                return result;
            } else {
                logger.info("请求内容中心 返回视频为空 multiConditionGetMedias  request={} skcontIds={}", JSON.toJSONString(request),JSON.toJSONString(homeRecommend));

            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean checkCategoryByChannel(Long categoryId, String channel) {
        SkCategory cc= skCategoryDAO.selectByParams(categoryId,"");
        if (null!=cc){
            return true;
        }
        return false;
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




}
