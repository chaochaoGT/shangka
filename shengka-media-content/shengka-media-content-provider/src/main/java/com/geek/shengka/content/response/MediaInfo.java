package com.geek.shengka.content.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class MediaInfo implements Serializable{
	private static final long serialVersionUID = 4306607959622137795L;
	/**视频id**/
	private String id;
	/**标题**/
	private String title;
	/**封面**/
    private String coverImage;
	/**视频地址**/
    private String url;
	/**视频时长**/
    private String duration;
	/****/
    private String fileId;
	/**点赞数**/
    private String giveThumbsNums;
	/**类型**/
    private String contentType;
	/**评论数*/
    private String commentNums;
	/**收藏数**/
    private String hasBeenCollected;
	/**观看次数*/
    private String watchedTimes;
	/**视频id**/
    private String recRequestId;
	/**视频id**/
    private String size;
	/**分值**/
    private long score;
	/**视频类型 0：短视频 1：小视频**/
    private Integer watchMode;
	/**作者编号**/
    private String authorId;
	/**作者名称**/
    private String nickname;
	/**作者头像**/
    private String avatar;
	/**金币展示时间**/
    private String showGold;
	/**获取金币时间**/
    private String gainGold;
	/**索引id**/
    private String indexId;
	/**类别编码**/
    private String contentCategoryCode;
	/**类别名称**/
    private String contentCategoryName;
    
    
    
    //是否喜欢标识 0-是，1-否
    private int likeFlag =1; 
    //是否关注 0-是，1-否
    private int attendFlag =1;
    /**@用户信息集合（json格式：[{"userId":1, "nickName":"zhangsan"}]   ）**/
    private String noticeUserJson;
    /**关联话题集合（json格式：[{"id":1, "topicName":"话题1"}]  ）**/
    private String topicJson;
    
    private int  userCount;
    
    
}
