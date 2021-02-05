package com.geek.shengka.content.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.geek.shengka.common.basemodel.BaseMediaInfo;
import com.geek.shengka.common.cache.CacheProvider;
import com.geek.shengka.common.proxy.ContentVideoInfoProxy;
import com.geek.shengka.common.request.BaseContentRequest;
import com.geek.shengka.common.util.CdnUrlUtils;
import com.geek.shengka.content.constans.SearchConstans;
import com.geek.shengka.content.entity.SkRankList;
import com.geek.shengka.content.entity.SkTopicConfig;
import com.geek.shengka.content.entity.vo.SkSearchModuleVO;
import com.geek.shengka.content.entity.vo.SkSearchSourceVO;
import com.geek.shengka.content.mapper.*;
import com.geek.shengka.content.request.SearchRequest;
import com.geek.shengka.content.service.SkSearchService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Filename: SkSearchServiceImpl
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/5 ;
 */
@Service
public class SkSearchServiceImpl implements SkSearchService {
    private Logger logger = LoggerFactory.getLogger(SkSearchServiceImpl.class);

    @Autowired
    private SkSearchPageConfigMapper skSearchPageConfigMapper;

    @Autowired
    private SkSearchModuleMappingMapper skSearchModuleMappingMapper;

    @Autowired
    private SkRankMappingMapper skRankMappingMapper;

    @Autowired
    private SkRankListMapper skRankListMapper;

    @Autowired
    private SkTopicConfigDAO skTopicConfigDAO;

    @Autowired
    private SkUserBaseInfoDAO skUserBaseInfoDAO;

    @Autowired
    private SkPublishVideoDAO skPublishVideoDAO;

    @Autowired
    private SkVideoLikeDAO skVideoLikeDAO;
    @Autowired
    private SkFansDAO skFansDAO;


    @Autowired
    private ContentVideoInfoProxy contentVideoInfoProxy;


    @Override
    public List<SkSearchModuleVO> searchPages(String channel) {
        String key = String.format(SearchConstans.SEARCH_HOME_PAGE_DATA, channel);
        List<SkSearchModuleVO> configs = CacheProvider.getObject(key, List.class);
//        List<SkSearchModuleVO> configs = null;
        //config-->sourcelsit
        if (CollectionUtils.isEmpty(configs)) {
            configs = skSearchPageConfigMapper.selectSkSearchPageConfigList(new HashMap<>(0));
            try {
                if (!CollectionUtils.isEmpty(configs)) {
                    configs.forEach(c -> {
                        getSourceList(c, SearchConstans.DEFAULT_PAGE_START,
                                SearchConstans.DEFAULT_PAGE_COUNT);
                    });
                    CacheProvider.setObject(key, configs, SearchConstans.DEFAULT_REDIS_TIME);
                } else {
                    logger.warn("searchPages 搜索页配置数据为 空 ");
                }
            } catch (Exception e) {
                logger.error("searchPages error msg={} e={}", e.getMessage(), e);
            }
        }

        return configs;

    }

    /**
     * 获取资源集合
     *
     * @param c
     * @param defaultPageStart
     * @param defaultPageCount
     * @return
     */
    private SkSearchModuleVO getSourceList(SkSearchModuleVO c, Integer defaultPageStart, Integer defaultPageCount) {
        //声咖热搜
        if (SearchConstans.SEARCH_CODE_HOT.equals(c.getModuleCode())) {
            //视频
            if (SearchConstans.MODULE_TYPE_1.compareTo(c.getModuleType().intValue()) == 0) {
                c.setSourceList(getHotVideosOrFindWonders(c, defaultPageStart, defaultPageCount));
            }
            //话题
            else {
                c.setSourceList(getHotTopicSearchs(c, defaultPageStart, defaultPageCount));
                //匹配前端逻辑 3：话题
                c.setModuleType(SearchConstans.MODULE_TYPE_3.byteValue());
            }
        }
        //最热视频
        else if (SearchConstans.SEARCH_CODE_HOTEST.equals(c.getModuleCode())) {
            c.setSourceList(getHotVideosOrFindWonders(c, defaultPageStart, defaultPageCount));
        }
        //人气榜单
        else if (SearchConstans.SEARCH_CODE_POPU.equals(c.getModuleCode())) {
            c.setSourceList(getPopularRankList(c, defaultPageStart, defaultPageCount));
        }
        //发现精彩
        else if (SearchConstans.SEARCH_CODE_FIND.equals(c.getModuleCode())) {
            c.setSourceList(getHotVideosOrFindWonders(c, defaultPageStart, defaultPageCount));
        }
        return c;
    }

    /**
     * 热点视频或发现精彩
     *
     * @param c
     * @param startRecordNumb
     * @param pageCount
     * @return list
     */
    private List<SkSearchSourceVO> getHotVideosOrFindWonders(SkSearchModuleVO c, Integer startRecordNumb, Integer pageCount) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("configId", c.getId());
        paraMap.put("startRecordNumb", startRecordNumb);
        paraMap.put("pageCount", pageCount);
        List<String> sourceIds = skSearchModuleMappingMapper.selectSourceList(paraMap);
        return getManyMedias(sourceIds);
    }

    /**
     * 内容中心-视频ids获取具体信息
     *
     * @param sourceIds
     * @return
     */
    @Override
    public List<SkSearchSourceVO> getManyMedias(List<String> sourceIds) {
        if (CollectionUtils.isEmpty(sourceIds)) {
            return new ArrayList<>();
        }
        List<SkSearchSourceVO> result = new ArrayList<>();
        List<BaseMediaInfo> manyMedias = contentVideoInfoProxy.multiConditionGetMedias(BaseContentRequest.builder().videoIds(sourceIds).pageNo(SearchConstans.default_pageNo).pageSize(sourceIds.size()).build());
        if (!CollectionUtils.isEmpty(manyMedias)) {
            List<SkSearchSourceVO> collect = manyMedias.stream().map(b -> {
                SkSearchSourceVO vo = new SkSearchSourceVO();
                vo.setSourceId(b.getId());
                vo.setSourceName(b.getTitle());
                vo.setSourceCoverUrl(CdnUrlUtils.transferCdn(b.getCoverImage()));
                vo.setSourceUrl(CdnUrlUtils.transferCdn(b.getUrl()));
                vo.setSourceIconUrl(b.getAvatar());
                vo.setCreateBy(b.getNickname());
                vo.setAvatarUrl(b.getAvatar());
                vo.setAvatarId(b.getAuthorId());
                vo.setWatchMode(b.getWatchMode());
                vo.setWatchTimes(b.getWatchedTimes());
                vo.setGiveThumbsNums(b.getGiveThumbsNums());
                vo.setContentCategoryCode(b.getContentCategoryCode());
                vo.setContentCategoryName(b.getContentCategoryName());
                //视频
                vo.setSourceType(SearchConstans.MODULE_TYPE_1);
                return vo;
            }).collect(Collectors.toList());
            result = sortResultVO(sourceIds, collect);
        } else {
            logger.info("请求内容中心 返回视频为空 getManyMedias  request={} sourceIds.size={}", JSON.toJSONString(sourceIds), sourceIds.size());
        }
        return result;

    }

    /**
     * 更多人气榜单
     *
     * @param c
     * @param startRecordNumb
     * @param pageCount
     * @return
     */
    private List<SkSearchSourceVO> getPopularRankList(SkSearchModuleVO c, Integer startRecordNumb, Integer pageCount) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("configId", c.getId());
        paraMap.put("startRecordNumb", startRecordNumb);
        paraMap.put("pageCount", pageCount);
        List<SkSearchSourceVO> ranks = skSearchModuleMappingMapper.getPopularRankList(paraMap);
        getTopOneUserInfo(ranks);

        return ranks;
    }

    /**
     * 获取topOne 用户信息
     *
     * @param ranks
     */
    private void getTopOneUserInfo(List<SkSearchSourceVO> ranks) {
        if (!CollectionUtils.isEmpty(ranks)) {
            //人气榜单具体资源
            ranks.forEach(r -> {
                Map<String, Object> paraMap = new HashMap<>();
                paraMap.put("rankId", r.getSourceId());
                paraMap.put("sourceType", r.getSourceType());
                SkSearchSourceVO vo = skRankMappingMapper.getTopOneObjInfo(paraMap);
                //视频
                if (null != vo && SearchConstans.MODULE_TYPE_1.compareTo(r.getSourceType()) == 0) {
                    r.setSourceNo(vo.getSourceId());
                    r.setSourceDesc(vo.getSourceName());
                    r.setSourceIconUrl(CdnUrlUtils.transferCdn(vo.getSourceIconUrl()));
                    r.setSourceUrl(CdnUrlUtils.transferCdn(vo.getSourceUrl()));
                }
                //用户
                if (null != vo && SearchConstans.MODULE_TYPE_2.compareTo(r.getSourceType()) == 0) {
                    r.setSourceDesc(vo.getSourceName());
                    r.setSourceIconUrl(CdnUrlUtils.transferCdn(vo.getSourceIconUrl()));
                }
                //话题
                if (null != vo && SearchConstans.MODULE_TYPE_3.compareTo(r.getSourceType()) == 0) {
                    r.setSourceDesc(vo.getSourceName());
                    r.setSourceIconUrl(CdnUrlUtils.transferCdn(vo.getSourceIconUrl()));
                    r.setTopicTag(vo.getTopicTag());
                    r.setPushNumbs(vo.getPushNumbs());
                }
            });
        }
    }

    /**
     * 更多热点声咖-话题
     *
     * @param c
     * @return
     */
    private List<SkSearchSourceVO> getHotTopicSearchs(SkSearchModuleVO c, Integer startRecordNumb, Integer pageCount) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("configId", c.getId());
        paraMap.put("startRecordNumb", startRecordNumb);
        paraMap.put("pageCount", pageCount);
        return skSearchModuleMappingMapper.getHotTopicSearchs(paraMap);
    }

    /**
     * 排序
     *
     * @param sourceIds
     * @param hotTopicSearchs
     * @return list
     */
    private List<SkSearchSourceVO> sortResultVO(List<String> sourceIds, List<SkSearchSourceVO> hotTopicSearchs) {
        if (!CollectionUtils.isEmpty(hotTopicSearchs)) {
            Map<String, SkSearchSourceVO> collect =
                    hotTopicSearchs.stream().collect(Collectors.toMap(SkSearchSourceVO::getSourceId, sk -> sk));
            List<SkSearchSourceVO> collect1 = sourceIds.stream().filter(id -> null != collect.get(id)).map(vo -> {
                return collect.get(vo);
            }).collect(Collectors.toList());

            return collect1;
        }
        return new ArrayList<>(5);
    }


    @Override
    public List<SkSearchSourceVO> searchByLabel(SearchRequest param) {
        List<SkSearchSourceVO> result = new ArrayList<>(6);
        //1视频
        if (SearchConstans.MODULE_TYPE_1.compareTo(param.getLabel()) == 0) {
            List<String> videoIds = skPublishVideoDAO.searchVideoIdsByKeyWord(param);
            result = getManyMedias(videoIds);
            if (param.getUserId() != null && param.getUserId() > 0) {
                //如果用户登录，则初始化数据是否曾被当前用户所喜欢
                initSearchSourceLike(result, videoIds, param.getUserId());
            }
        }
        //2用户
        else if (SearchConstans.MODULE_TYPE_2.compareTo(param.getLabel()) == 0) {
            List<SkSearchSourceVO> users = skUserBaseInfoDAO.searchUserByKeyWord(param);
            if (param.getUserId() != null && param.getUserId() > 0) {
                //如果用户登录，则初始化数据是否曾被当前用户所关注
                initSearchSourceFans(users, param.getUserId());
            }
            result = users;
        }
        //3话题
        else if (SearchConstans.MODULE_TYPE_3.compareTo(param.getLabel()) == 0) {
            List<SkTopicConfig> topics = skTopicConfigDAO.searchTopicByKeyWord(param);
            //则初始化话题数据
            result = initSearchSourceTopic(topics, param.getUserId());
        } else {
            logger.warn("label is error, lable={}", param.getLabel());
        }
        return result;
    }

    /**
     * 则初始化话题数据F
     *
     * @param topics
     * @param userId
     * @return java.util.List<com.geek.shengka.content.entity.vo.SkSearchSourceVO>
     * @author qubianzhong
     * @Date 16:24 2019/8/19
     */
    private List<SkSearchSourceVO> initSearchSourceTopic(List<SkTopicConfig> topics, Long userId) {
        List<SkSearchSourceVO> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(topics)) {
            result = topics.stream().map(t -> {
                SkSearchSourceVO vo = new SkSearchSourceVO();
                vo.setSourceId(String.valueOf(t.getId()));
                vo.setSourceName(t.getTopicName());
                vo.setSourceIconUrl(t.getTopicLogo());
                vo.setSourceDesc(t.getTopicIntro());
                if (null != t.getPushCount()) {
                    vo.setPushNumbs(t.getPushCount() >= 10000 ? (t.getPushCount() / 10000 + "万") :
                            String.valueOf(t.getPushCount()));
                } else {
                    vo.setPushNumbs("0");
                }
                //话题播放量
                if (null != t.getWatchCount()) {
                    vo.setWatchTimes(t.getWatchCount() >= 10000 ? (t.getWatchCount() / 10000 + "万") :
                            String.valueOf(t.getWatchCount()));
                } else {
                    vo.setWatchTimes("0");
                }
                vo.setSourceType(SearchConstans.MODULE_TYPE_3);
                return vo;
            }).collect(Collectors.toList());
        }
        return result;
    }

    /**
     * 如果用户登录，则初始化数据是否曾被当前用户所关注
     *
     * @param users
     * @param userId
     * @return void
     * @author qubianzhong
     * @Date 15:34 2019/8/19
     */
    private void initSearchSourceFans(List<SkSearchSourceVO> users, Long userId) {
        if (!CollectionUtils.isEmpty(users)) {
            List<String> searchUserIds = users.stream().map(SkSearchSourceVO::getSourceId).collect(Collectors.toList());
            List<String> fansUserIds = skFansDAO.getStateByUserIds(searchUserIds, userId);
            users.forEach(u -> {
                u.setFansNums(Integer.valueOf(u.getFansNums()) >= 10000 ? (Integer.valueOf(u.getFansNums()) / 10000 + "万") :
                        u.getFansNums());
                u.setSourceType(SearchConstans.MODULE_TYPE_2);
                if (fansUserIds.contains(u.getSourceId())) {
                    u.setAttentionFlag(1);
                } else {
                    u.setAttentionFlag(0);
                }
            });
        }
    }

    /**
     * 如果用户登录，则初始化数据是否曾被当前用户所喜欢
     *
     * @param result   data
     * @param videoIds 视频IDS
     * @param userId   用户ID
     * @return void
     * @author qubianzhong
     * @Date 15:22 2019/8/19
     */
    private List<SkSearchSourceVO> initSearchSourceLike(List<SkSearchSourceVO> result, List<String> videoIds, Long userId) {
        if (CollectionUtils.isEmpty(videoIds)){
            return result;
        }
        // 是否喜欢
        List<String> likeVideoIds = skVideoLikeDAO.myVideoLikeList(videoIds, userId);
        if (!CollectionUtils.isEmpty(likeVideoIds)) {
            result.forEach(vo -> {
                if (likeVideoIds.contains(vo.getSourceId())) {
                    vo.setAttentionFlag(SearchConstans.MODULE_TYPE_1);
                }
            });
        }
        return result;
    }

    /**
     * 获取搜索页面配置的模块
     *
     * @param channel
     * @return
     */
    private Map<String, SkSearchModuleVO> getModuleCodesFromRedis(String channel) {
        String key = String.format(SearchConstans.SEARCH_MODULE_CODES, channel);
        String s = CacheProvider.get(key);
        Map<String, SkSearchModuleVO> codes = JSON.parseObject(s, new TypeReference<Map<String, SkSearchModuleVO>>() {
        });
        if (CollectionUtils.isEmpty(codes)) {

            List<SkSearchModuleVO> skSearchModuleVOS =
                    skSearchPageConfigMapper.selectSkSearchPageConfigList(new HashMap<>(0));
            codes = skSearchModuleVOS.stream().collect(Collectors.toMap(SkSearchModuleVO::getModuleCode, vo -> vo));
            if (CollectionUtils.isEmpty(codes)) {
                logger.warn("method getModuleCodes  get code empty !!!");
            }
            CacheProvider.setObject(key, codes, SearchConstans.DEFAULT_REDIS_TIME);
        }
        return codes;
    }


    @Override
    public List<SkSearchSourceVO> seeMoreSourceList(SearchRequest param) {
        if (StringUtils.isEmpty(param.getModuleCode())) {
            logger.warn(" moduleCode is empty");
            return new ArrayList<>();
        }
        List<SkSearchSourceVO> sourceVOS = null;
        //moduleCode
        Map<String, SkSearchModuleVO> moduleCodes = getModuleCodesFromRedis("");
        SkSearchModuleVO c = moduleCodes.get(param.getModuleCode());
        if (null != c && SearchConstans.SEARCH_CODE_HOT.equals(c.getModuleCode())
                && StringUtils.isNotEmpty(c.getSourceIds())
                //此处2未搜索配置表的类型2 话题
                && SearchConstans.MODULE_TYPE_2.compareTo(c.getModuleType().intValue()) == 0) {
            param.setStartRecordNumb(SearchConstans.DEFAULT_PAGE_START);
            param.setPageCount(SearchConstans.DEFAULT_PAGE_COUNT);
        }
        //资源集合
        c = getSourceList(c, param.getStartRecordNumb(), param.getPageCount());
        sourceVOS = c.getSourceList();
        return sourceVOS;
    }

    @Override
    public List<SkSearchSourceVO> popularRankInfo(SearchRequest param) {
        SkRankList sk = skRankListMapper.selectByPrimaryKey(Integer.valueOf(param.getSourceId()));
        if (!Objects.isNull(sk)) {
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("rankId", sk.getId());
            paraMap.put("sourceType", sk.getRankType());
            paraMap.put("userId", Objects.isNull(param.getUserId()) ? 0 : param.getUserId());
            List<SkSearchSourceVO> vos = skRankMappingMapper.getPopularRankList(paraMap);
            try {
                if (!CollectionUtils.isEmpty(vos)) {
                    //视频
                    if (SearchConstans.MODULE_TYPE_1.compareTo(Integer.valueOf(param.getSourceType())) == 0) {
                        List<String> collect =
                                vos.stream().map(SkSearchSourceVO::getSourceId).collect(Collectors.toList());
                        List<SkSearchSourceVO> manyMedias = getManyMedias(collect);
                        return manyMedias.stream().sorted(Comparator.comparing(SkSearchSourceVO::intWatchTimes).reversed()).collect(Collectors.toList());
                    } else {
                        //话题排序
                        vos.forEach(s -> {
                            //用户
                            if (SearchConstans.MODULE_TYPE_2.compareTo(Integer.valueOf(param.getSourceType())) == 0) {
                                s.setFansNums(Integer.valueOf(s.getFansNums()) >= 10000 ?
                                        (Integer.valueOf(s.getFansNums()) / 10000 + "万") :
                                        s.getFansNums());
                            }
                            //话题
                            if (SearchConstans.MODULE_TYPE_3.compareTo(Integer.valueOf(param.getSourceType())) == 0) {
                                s.setPushNumbs(Integer.valueOf(s.getPushNumbs()) >= 10000 ?
                                        (Integer.valueOf(s.getPushNumbs()) / 10000 + "万") :
                                        s.getPushNumbs());
                                //话题播放量
                                if (StringUtils.isNotEmpty(s.getWatchTimes())) {
                                    s.setWatchTimes(Integer.valueOf(s.getWatchTimes()) >= 10000 ?
                                            (Integer.valueOf(s.getWatchTimes()) / 10000 + "万") : s.getWatchTimes());
                                } else {
                                    s.setWatchTimes("0");
                                }
                            }
                        });
                    }
                    return vos;
                }
            } catch (Exception e) {
                logger.error("popularRankInfo  is error  e={}",e);
            }
        }
        return new ArrayList<>(5);
    }
}
