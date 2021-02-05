package com.geek.shengka.content.service.impl;

import com.geek.shengka.content.config.RecommendConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.geek.shengka.common.basemodel.BaseMediaInfo;
import com.geek.shengka.common.enums.UserBaseTypeEnum;
import com.geek.shengka.common.http.ContextTools;
import com.geek.shengka.common.mq.UserBaseDataMsg;
import com.geek.shengka.common.pool.ThreadPoolManager;
import com.geek.shengka.common.util.CdnUrlUtils;
import com.geek.shengka.common.util.UserContextHolder;
import com.geek.shengka.content.entity.SkFans;
import com.geek.shengka.content.entity.SkPublishVideo;
import com.geek.shengka.content.entity.SkTopicConfig;
import com.geek.shengka.content.entity.SkTopicVideo;
import com.geek.shengka.content.entity.SkUserBaseInfo;
import com.geek.shengka.content.entity.SkVideoLike;
import com.geek.shengka.content.entity.SkVoice;
import com.geek.shengka.content.entity.vo.NoticeUserJson;
import com.geek.shengka.content.entity.vo.SkCateMediaInfoVO;
import com.geek.shengka.content.entity.vo.SkMediaInfoVO;
import com.geek.shengka.content.entity.vo.SkSearchSourceVO;
import com.geek.shengka.content.mapper.SkFansDAO;
import com.geek.shengka.content.mapper.SkPublishVideoDAO;
import com.geek.shengka.content.mapper.SkTopicConfigDAO;
import com.geek.shengka.content.mapper.SkTopicVideoDAO;
import com.geek.shengka.content.mapper.SkUserBaseInfoDAO;
import com.geek.shengka.content.mapper.SkVideoLikeDAO;
import com.geek.shengka.content.mapper.SkVoiceDAO;
import com.geek.shengka.content.proxy.ContentRemoteProxy;
import com.geek.shengka.content.proxy.ContentVerifyRemoteProxy;
import com.geek.shengka.content.rabbitmq.ContentRabbitmqSender;
import com.geek.shengka.content.request.Address;
import com.geek.shengka.content.request.Addresses;
import com.geek.shengka.content.request.Author;
import com.geek.shengka.content.request.ContentBackRequest;
import com.geek.shengka.content.request.ContentDeleteVideoRequest;
import com.geek.shengka.content.request.ContentDetailVideoRequest;
import com.geek.shengka.content.request.ContentFriendMqRequest;
import com.geek.shengka.content.request.MediaVideoSource;
import com.geek.shengka.content.request.PublishMediaRequest;
import com.geek.shengka.content.request.ContentMediaRequest;
import com.geek.shengka.content.request.VideoMqRequest;
import com.geek.shengka.content.request.VoiceMqRequest;
import com.geek.shengka.content.response.MediaDetailInfo;
import com.geek.shengka.content.response.MediaInfo;
import com.geek.shengka.content.response.MediaReturnTopicInfo;
import com.geek.shengka.content.response.VoiceUserParam;
import com.geek.shengka.content.service.MediaService;
import com.geek.shengka.content.service.recommand.RecommandService;
import com.geek.shengka.content.service.recommand.entity.SmallVideoRecommandParam;
import com.geek.shengka.content.utils.UuidTools;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 视频
 */
@Service
public class MediaServiceImpl implements MediaService {
    private static final Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);

    @Autowired
    private ContentRemoteProxy contentRemoteProxy;
    @Autowired
    private SkTopicConfigDAO skTopicConfigDAO;
    @Autowired
    private SkPublishVideoDAO skPublishVideoDAO;
    @Autowired
    private ContentVerifyRemoteProxy contentVerifyRemoteProxy;
    @Autowired
    private ContentRabbitmqSender contentRabbitmqSender;
    @Autowired
    private SkVoiceDAO skVoiceDAO;
    @Autowired
    private SkVideoLikeDAO skVideoLikeDAO;
    @Autowired
    private SkFansDAO skFansDAO;
    @Autowired
    private SkUserBaseInfoDAO skUserBaseInfoDAO;
    @Autowired
    private RecommandService recommandService;
    @Autowired
    private SkTopicVideoDAO skTopicVideoDAO;

    @Autowired
    private RecommendConfig config;

    @Override
    public List<MediaInfo> getRemoteVideos(ContentMediaRequest param) {
        List<MediaInfo> infos = new ArrayList<MediaInfo>();

        SmallVideoRecommandParam recommandParam = new SmallVideoRecommandParam();
        recommandParam.setImei(ContextTools.getRequest().getHeader("imei"));
        recommandParam.setPageIndex(param.getPageNumber());
        recommandParam.setPageSize(config.getDefaultPageSize());
        List<BaseMediaInfo> baseMediaInfos = recommandService.recommandSmallVideos(recommandParam);

        if (CollectionUtils.isNotEmpty(baseMediaInfos)) {
            long userId = UserContextHolder.getUserIdByHeader();
            List<String> videoIds = baseMediaInfos.stream().map(BaseMediaInfo::getId).collect(Collectors.toList());
            List<SkMediaInfoVO>  dbJson =   skPublishVideoDAO.selectByVideoIds(videoIds);
            List<VoiceUserParam> voiceUserParams = skVoiceDAO.selectByUserCount(videoIds);

            if (userId >= 1L) {
                Set<String> attentionIds = baseMediaInfos.stream().map(BaseMediaInfo::getAuthorId).collect(Collectors.toSet());
                Set<String> myLikeVideos = new HashSet<String>(skVideoLikeDAO.myVideoLikeList(videoIds, userId));
                Set<String> attentionUsers = new HashSet<String>(skFansDAO.selectMyFans(userId, attentionIds));
                baseMediaInfos.stream().map(baseMediaInfo -> translation(baseMediaInfo,myLikeVideos,attentionUsers)).collect(Collectors.toList());
            }
            infos=baseMediaInfos.stream().map(baseMediaInfo -> translation(baseMediaInfo,dbJson,voiceUserParams)).collect(Collectors.toList());
        }

        return infos;
    }

    /***
     * 转化
     * @param baseMediaInfo
     * @param myLikeVideos
     * @param attentionUsers
     * @return
     */
    private final MediaInfo translation(BaseMediaInfo baseMediaInfo, Set<String> myLikeVideos, Set<String> attentionUsers) {
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setId(baseMediaInfo.getId());
        mediaInfo.setTitle(baseMediaInfo.getTitle());
        mediaInfo.setCoverImage(baseMediaInfo.getCoverImage());
        mediaInfo.setUrl(baseMediaInfo.getUrl());
        mediaInfo.setDuration(baseMediaInfo.getDuration());
        mediaInfo.setGiveThumbsNums(baseMediaInfo.getGiveThumbsNums());
        mediaInfo.setCommentNums(baseMediaInfo.getCommentNums());
        mediaInfo.setHasBeenCollected(baseMediaInfo.getHasBeenCollected());
        mediaInfo.setWatchedTimes(baseMediaInfo.getWatchedTimes());
        mediaInfo.setSize(baseMediaInfo.getSize());
        mediaInfo.setScore(baseMediaInfo.getScore());
        mediaInfo.setWatchMode(baseMediaInfo.getWatchMode());
        mediaInfo.setAuthorId(baseMediaInfo.getAuthorId());
        mediaInfo.setNickname(baseMediaInfo.getNickname());
        mediaInfo.setAvatar(baseMediaInfo.getAvatar());
        mediaInfo.setIndexId(baseMediaInfo.getIndexId());
        mediaInfo.setContentCategoryCode(baseMediaInfo.getContentCategoryCode());
        mediaInfo.setContentCategoryName(baseMediaInfo.getContentCategoryName());
        if (myLikeVideos!=null&&myLikeVideos.contains(baseMediaInfo.getId())) {
            mediaInfo.setLikeFlag(0);
        }
        if (attentionUsers!=null&&attentionUsers.contains(baseMediaInfo.getAuthorId())) {
            mediaInfo.setAttendFlag(0);
        }
        return mediaInfo;
    }

    /***
     * 转化
     * @param baseMediaInfo
     * @return
     */
    private final MediaInfo translation(BaseMediaInfo baseMediaInfo,List<SkMediaInfoVO> dbJson, List<VoiceUserParam> voiceUserParams) {
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.setId(baseMediaInfo.getId());
        mediaInfo.setTitle(baseMediaInfo.getTitle());
        mediaInfo.setCoverImage(baseMediaInfo.getCoverImage());
        mediaInfo.setUrl(baseMediaInfo.getUrl());
        mediaInfo.setDuration(baseMediaInfo.getDuration());
        mediaInfo.setGiveThumbsNums(baseMediaInfo.getGiveThumbsNums());
        mediaInfo.setCommentNums(baseMediaInfo.getCommentNums());
        mediaInfo.setHasBeenCollected(baseMediaInfo.getHasBeenCollected());
        mediaInfo.setWatchedTimes(baseMediaInfo.getWatchedTimes());
        mediaInfo.setSize(baseMediaInfo.getSize());
        mediaInfo.setScore(baseMediaInfo.getScore());
        mediaInfo.setWatchMode(baseMediaInfo.getWatchMode());
        mediaInfo.setAuthorId(baseMediaInfo.getAuthorId());
        mediaInfo.setNickname(baseMediaInfo.getNickname());
        mediaInfo.setAvatar(baseMediaInfo.getAvatar());
        mediaInfo.setIndexId(baseMediaInfo.getIndexId());
        mediaInfo.setContentCategoryCode(baseMediaInfo.getContentCategoryCode());
        mediaInfo.setContentCategoryName(baseMediaInfo.getContentCategoryName());
       try {
        if(CollectionUtils.isNotEmpty(dbJson)) {
        	for(SkMediaInfoVO jsonx:dbJson) {
        		if(jsonx.getId().equals(baseMediaInfo.getId())) {
        			String topicJson = jsonx.getTopicJson();
        			if(StringUtils.isNotBlank(topicJson)) {
        			    mediaInfo.setTopicJson(topicJson);
        			}else {
        				mediaInfo.setTopicJson(null);
        			}
        			String noticeUserJson = jsonx.getNoticeUserJson();
        			if(StringUtils.isNotBlank(noticeUserJson)) {
        				mediaInfo.setNoticeUserJson(noticeUserJson);
        			}else {
        				mediaInfo.setNoticeUserJson(null);
        			}
        		}
        	}
        }
        if(CollectionUtils.isNotEmpty(voiceUserParams)) {
        	for(VoiceUserParam VoiceUserParam:voiceUserParams) {
        		if(VoiceUserParam.getVideoId().equals(baseMediaInfo.getId())) {
        			mediaInfo.setUserCount(VoiceUserParam.getUserCount());
        		}
        	}
        }
       }catch (Exception e) {
		 
	   }
        return mediaInfo;
    }


    @Override
    public MediaDetailInfo getRemoteVideoDetail(String videoId) {
    	MediaDetailInfo mediaDetailInfo = new MediaDetailInfo();
    	SkPublishVideo skPublishVideo = skPublishVideoDAO.selectByPrimaryKey(videoId);
    	if(null==skPublishVideo) {
    		return null;
    	}
		String topicJson = skPublishVideo.getTopicJson();
		if(StringUtils.isNotBlank(topicJson)) {
			mediaDetailInfo.setTopicJson(topicJson);
		}else {
			mediaDetailInfo.setTopicJson(null);
		}
		
		String noticeUserJson = skPublishVideo.getNoticeUserJson();
		if(StringUtils.isNotBlank(noticeUserJson)) {
			mediaDetailInfo.setNoticeUserJson(noticeUserJson);
		}else {
			mediaDetailInfo.setNoticeUserJson(null);
		}
		
    	if(skPublishVideo.getEnable()==0) {
    		mediaDetailInfo.setId(skPublishVideo.getId());
    		mediaDetailInfo.setTitle(skPublishVideo.getTitle());
    		mediaDetailInfo.setCoverImage(CdnUrlUtils.transferCdn(skPublishVideo.getCoverImageUrl()));
    		mediaDetailInfo.setUrl(CdnUrlUtils.transferCdn(skPublishVideo.getVideoUrl()));
    		mediaDetailInfo.setWatchMode(skPublishVideo.getWatchMode());
    		mediaDetailInfo.setAuthorId(String.valueOf(skPublishVideo.getPublishUserId()));
    		mediaDetailInfo.setContentType("1");
    		mediaDetailInfo.setGiveThumbsNums("0");
    		mediaDetailInfo.setCommentNums("0");
    		mediaDetailInfo.setWatchedTimes("0");
    		SkUserBaseInfo skUserBaseInfo = skUserBaseInfoDAO.selectByUser(skPublishVideo.getPublishUserId());
    		if(null!=skUserBaseInfo) {
    			mediaDetailInfo.setNickname(skUserBaseInfo.getNickName());
    			mediaDetailInfo.setAvatar(CdnUrlUtils.transferCdn(skUserBaseInfo.getUserIcon()));
    		}
    	    
    	}else if(skPublishVideo.getEnable()==1){
            ContentDetailVideoRequest param = new ContentDetailVideoRequest();
            param.setVideoId(videoId);
            mediaDetailInfo = contentRemoteProxy.getRemoteVideoDetail(param);
            try {
    		SkUserBaseInfo skUserBaseInfo = skUserBaseInfoDAO.selectByUser(Long.valueOf(mediaDetailInfo.getAuthorId()));
    		if(null!=skUserBaseInfo) {
    			mediaDetailInfo.setNickname(skUserBaseInfo.getNickName());
    			mediaDetailInfo.setAvatar(CdnUrlUtils.transferCdn(skUserBaseInfo.getUserIcon()));
    		}
            }catch (Exception e) {
				logger.error("获取个人头像名称失败");
			}
    		
    	}

        long userId = UserContextHolder.getUserIdByHeader();
        if (userId >= 1L && null != mediaDetailInfo) {
            try {
                SkVideoLike skVideoLike = skVideoLikeDAO.selectByUidAndVideoId(userId, mediaDetailInfo.getId());
                if (null != skVideoLike) {
                    mediaDetailInfo.setLikeFlag(0);
                }
                SkFans skFans = new SkFans();
                skFans.setUserId(userId);
                long authorId = Long.parseLong(mediaDetailInfo.getAuthorId());
                skFans.setAttentionUserId(authorId);
                List<SkFans> SkFansBean = skFansDAO.selectByFans(skFans);
                if (CollectionUtils.isNotEmpty(SkFansBean)) {
                    mediaDetailInfo.setAttendFlag(0);
                }
            } catch (Exception e) {
                logger.error("转化关注和喜欢失败");
            }
        }


        return mediaDetailInfo;
    }


    @Override
    public int publishMedia(PublishMediaRequest param) {
        int flag = 0;
        SkPublishVideo skPublishVideo = new SkPublishVideo();
        BeanUtils.copyProperties(param, skPublishVideo);
        String topicIds = "";
        String noticeUserIds = param.getNoticeUserIds();
        List<MediaReturnTopicInfo> mediaReturnTopicInfos = new ArrayList<MediaReturnTopicInfo>();
        /**解析标题中#话题@好友**/
        String title = param.getTitle();
        if (title.contains("#")) {
            String[] topics = title.split("#");
            for (int i = 1; i < topics.length; i++) {
                String topicName = topics[i];
                if (topics[i].contains("@")) {
                    topicName = topics[i].substring(0, topics[i].indexOf("@"));
                }
                SkTopicConfig skTopicConfig = skTopicConfigDAO.selectByTopicName("#"+topicName);
                if (null != skTopicConfig) {
                    topicIds += skTopicConfig.getId() + ",";
                    MediaReturnTopicInfo mediaReturnTopicInfo = new MediaReturnTopicInfo();
                    mediaReturnTopicInfo.setId(skTopicConfig.getId());
                    mediaReturnTopicInfo.setTopicName("#"+topicName);
                    mediaReturnTopicInfos.add(mediaReturnTopicInfo);
                } else {
                	try {
	                    SkTopicConfig skTopicConfigBean = new SkTopicConfig();
	                    skTopicConfigBean.setUserId(param.getPublishUserId());
	                    skTopicConfigBean.setTopicName("#"+topicName);
	                    skTopicConfigBean.setTopicType((byte) 1);
	                    skTopicConfigBean.setTopicTag((byte) 3);
	                    skTopicConfigBean.setTopicLogo("https://shengka-real.oss-cn-shanghai.aliyuncs.com/apk/image/ccdf8e2a84afe89e09e0be3b3c32b681.png");
	                    skTopicConfigDAO.insertTopic(skTopicConfigBean);
	                     
	                    MediaReturnTopicInfo mediaReturnTopicInfo = new MediaReturnTopicInfo();
	                    mediaReturnTopicInfo.setId(skTopicConfigBean.getId());
	                    mediaReturnTopicInfo.setTopicName("#"+topicName);
	                    mediaReturnTopicInfos.add(mediaReturnTopicInfo);
	                    topicIds += skTopicConfigBean.getId() + ",";
                	}catch (Exception e) {
                		 logger.error("skTopicConfigDAO.insertTopic()-->>>>>>>>>>>>>>>>>>>>>>", "新增主题失败");
					}
                   
                }

            }
        }

        skPublishVideo.setTopicIds(topicIds);
        skPublishVideo.setNoticeUserIds(noticeUserIds);
        skPublishVideo.setId(UuidTools.getUUIDString());
        skPublishVideo.setPublishUserId(UserContextHolder.getUserIdByHeader());
        
        List<SkSearchSourceVO> mediaReturnUserInfos = skUserBaseInfoDAO.selectByIds(noticeUserIds);
        List<NoticeUserJson> NoticeUserJsons = new ArrayList<NoticeUserJson>();
        if (CollectionUtils.isNotEmpty(mediaReturnUserInfos)) {
        	for(SkSearchSourceVO skSearchSourceVO:mediaReturnUserInfos) {
        		NoticeUserJson noticeUserJson = new NoticeUserJson();
        		noticeUserJson.setUserId(Long.valueOf(skSearchSourceVO.getSourceId()));
        		noticeUserJson.setNickName(skSearchSourceVO.getSourceName());
        		NoticeUserJsons.add(noticeUserJson);
        	}
        	String NoticeUserJsonsStr = JSON.toJSONString(NoticeUserJsons);
        	if(StringUtils.isNotBlank(NoticeUserJsonsStr)) {
                skPublishVideo.setNoticeUserJson(NoticeUserJsonsStr);
        	}else {
        		skPublishVideo.setNoticeUserJson(null);
        	}
        } else {
            skPublishVideo.setNoticeUserJson(null);
        }
        if (CollectionUtils.isNotEmpty(mediaReturnTopicInfos)) {
        	String mediaReturnTopicInfosJson = JSON.toJSONString(mediaReturnTopicInfos);
        	if(StringUtils.isNotBlank(mediaReturnTopicInfosJson)) {
        		skPublishVideo.setTopicJson(mediaReturnTopicInfosJson);
        	}else {
        		 skPublishVideo.setTopicJson(null);
        	}
        } else {
            skPublishVideo.setTopicJson(null);
        }
         
//        int fans = title.indexOf("@");
//        int topic = title.indexOf("#");
//        String titleBean = null;
//        if(fans>0&&topic>0) {
//        	   if(fans>topic) {
//               	titleBean = title.substring(0,topic);
//               }else if(fans<topic) {
//               	titleBean = title.substring(0,fans);
//               }else {
//               	titleBean = title;
//               }
//        }else if(fans>0&&topic<0) {
//        	titleBean = title.substring(0,fans);
//        }else if(fans<0&&topic>0) {
//        	titleBean = title.substring(0,topic);
//        }else {
//         	titleBean = title;
//        }
     
        skPublishVideo.setTitle(title);
        flag = skPublishVideoDAO.insertSelective(skPublishVideo);
        param.setContentId(skPublishVideo.getId());
        //消费者， 维护用户冗余基础数据 MQ语音
// 		UserBaseDataMsg messageData = new UserBaseDataMsg();
// 		messageData.setUserId(UserContextHolder.getUserIdByHeader());
// 		messageData.setNum(1);
// 		messageData.setCode(UserBaseTypeEnum.PUBLISH_NUM.getCode());
// 		contentRabbitmqSender.sendUserActionMessage(messageData);
        SkUserBaseInfo skUserBaseInfo = new SkUserBaseInfo();
        skUserBaseInfo.setUserId(UserContextHolder.getUserIdByHeader());
        skUserBaseInfo.setPublishNum(1);
        skUserBaseInfoDAO.updateUserBaseContentNum(skUserBaseInfo);

        ThreadPoolManager.addTask(new UserContentTask(param));

        return flag;
    }

    @Override
    public int delVideo(String videoId) {
        skPublishVideoDAO.deleteByPrimaryKey(videoId);
        ContentDeleteVideoRequest contentDeleteVideoRequest = new ContentDeleteVideoRequest();
        contentDeleteVideoRequest.setStatus("2");
        contentDeleteVideoRequest.setVideoId(videoId);
        contentRemoteProxy.deleteRemoteVideos(contentDeleteVideoRequest);
        return 0;
    }


    @Override
    public int syncMeidaInfo(ContentBackRequest param) {


        if (1 == param.getContentType()) {
            //更新审核视频结果
            SkPublishVideo skPublishVideo = skPublishVideoDAO.selectByPrimaryKey(param.getContentId());
            if (null != skPublishVideo) {
                skPublishVideo.setEnable((byte) param.getState());
                skPublishVideo.setRemark(param.getReason());
                skPublishVideoDAO.updateByPrimaryKeySelective(skPublishVideo);

                try {
                	
                    //任务MQ发布视频
                    VideoMqRequest message = new VideoMqRequest();
                    message.setType(1);
                    message.setUserId(skPublishVideo.getPublishUserId());
                    contentRabbitmqSender.sendVideoMessage(message);

                    //@好友通知
                    if (StringUtils.isNotBlank(skPublishVideo.getNoticeUserIds())) {
                        List<String> noticeUserIds = Arrays.asList(skPublishVideo.getNoticeUserIds().split(","));
                        for (String noticeUserId : noticeUserIds) {
                            ContentFriendMqRequest messageNotice = new ContentFriendMqRequest();
                            messageNotice.setType(2);
                            messageNotice.setMediaId(skPublishVideo.getId());
                            messageNotice.setUserId(Long.valueOf(noticeUserId));
                            contentRabbitmqSender.sendFriendMessage(messageNotice);
                        }
                    }

                    if (3 == param.getState()) {
                        //消费者， 维护用户冗余基础数据 MQ语音
//		 	 		UserBaseDataMsg messageData = new UserBaseDataMsg();
//		 	 		messageData.setUserId(skPublishVideo.getPublishUserId());
//		 	 		messageData.setNum(-1);
//		 	 		messageData.setCode(UserBaseTypeEnum.PUBLISH_NUM.getCode());
//		 	 		contentRabbitmqSender.sendUserActionMessage(messageData);

                        SkUserBaseInfo skUserBaseInfo = new SkUserBaseInfo();
                        skUserBaseInfo.setUserId(UserContextHolder.getUserIdByHeader());
                        skUserBaseInfo.setPublishNum(-1);
                        skUserBaseInfoDAO.updateUserBaseContentNum(skUserBaseInfo);
                    }
                 
                    String topicIds = skPublishVideo.getTopicIds();
                    if(StringUtils.isNotBlank(topicIds)) {
                    	String[] ids = topicIds.split(",");
                    	for(int x=0;x<ids.length;x++) {
	                    		SkTopicVideo skTopicVideo = new SkTopicVideo();
	                    		String str  = ids[x];
                    			skTopicVideo.setTopicId(Long.valueOf(str));
                    			skTopicVideo.setVideoId(skPublishVideo.getId());
                    			skTopicVideo.setSeq(x);
                    			skTopicVideoDAO.insertSelective(skTopicVideo);
                    	}
                    }
                    
                    
                } catch (Exception e) {
                    logger.error("发送MQ失败" + e.getMessage(), e);
                }

            }


        } else if (2 == param.getContentType()) {
            //更新语音评论结果
            //更新审核视频结果
            SkVoice skVoice = skVoiceDAO.selectByPrimaryKey(param.getContentId());

            if (null != skVoice) {
                skVoice.setEnable((byte) param.getState());
                skVoice.setRemark(param.getReason());
                skVoiceDAO.updateByPrimaryKeySelective(skVoice);

                try {
                    //任务MQ发布语音
                    VoiceMqRequest message = new VoiceMqRequest();
                    message.setType(2);
                    message.setUserId(skVoice.getUserId());
                    contentRabbitmqSender.sendVoiceMessage(message);

                    //通知MQ语音
                    ContentFriendMqRequest messageNotice = new ContentFriendMqRequest();
                    messageNotice.setType(1);
                    messageNotice.setMediaId(param.getContentId());
                    messageNotice.setUserId(skVoice.getUserId());
                    messageNotice.setTime(System.currentTimeMillis());
                    contentRabbitmqSender.sendFriendMessage(messageNotice);

                    if (3 == param.getState()) {
                        //消费者， 维护用户冗余基础数据 MQ语音
//		 	 		UserBaseDataMsg messageData = new UserBaseDataMsg();
//		 	 		messageData.setUserId(skVoice.getUserId());
//		 	 		messageData.setNum(-1);
//		 	 		messageData.setCode(UserBaseTypeEnum.VOICE_NUM.getCode());
//		 	 		contentRabbitmqSender.sendUserActionMessage(messageData);
                        SkUserBaseInfo skUserBaseInfo = new SkUserBaseInfo();
                        skUserBaseInfo.setUserId(UserContextHolder.getUserIdByHeader());
                        skUserBaseInfo.setVoiceNum(-1);
                        skUserBaseInfoDAO.updateUserBaseContentNum(skUserBaseInfo);

                    }

                } catch (Exception e) {
                    logger.error("发送MQ失败" + e.getMessage(), e);
                }
            }


        }


        return 0;
    }


    /**
     * 调用内容审核 中心
     */
    class UserContentTask implements Runnable {
        private PublishMediaRequest param;

        public UserContentTask(PublishMediaRequest param) {
            super();
            this.param = param;
        }

        @Override
        public void run() {
            try {

                MediaVideoSource mediaVideoSource = new MediaVideoSource();
                mediaVideoSource.setTitle(param.getTitle());
                mediaVideoSource.setCoverImage(param.getCoverImageUrl());
                mediaVideoSource.setUrl(param.getVideoUrl());
                mediaVideoSource.setAuthorId(String.valueOf(param.getPublishUserId()));
                mediaVideoSource.setId(param.getContentId());
                Address address = new Address();
                address.setUrl(param.getVideoUrl());
                address.setDuration(param.getDuration());
                address.setWidth(param.getWidth());
                address.setHeight(param.getHeight());
                address.setSize(param.getSize());

                Addresses addresses = new Addresses();
                addresses.setHd(address);
                mediaVideoSource.setAddresses(addresses);
                Author author = new Author();
                author.setAvatar(param.getAvatarUrl());
                author.setNickname(param.getNickname());
                author.setId(String.valueOf(param.getPublishUserId()));
                mediaVideoSource.setAuthor(author);
                mediaVideoSource.setWatchMode(param.getWatchMode());
                mediaVideoSource.setCategory("20001");
                contentVerifyRemoteProxy.uploadVideo(mediaVideoSource);
            } catch (Exception e) {

            } finally {
                this.clean();
            }
        }

        public void clean() {
            this.param = null;
        }

    }


}
