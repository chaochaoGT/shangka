package com.geek.shengka.content.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class MediaDetailInfo implements Serializable{

	private String id;
	private String title;
	private String coverImage;
	private String url;
	private String fileId;
	private String giveThumbsNums;
	private String contentType;
	private String commentNums;
	private String hasBeenCollected;
    private String watchedTimes;
    private String recRequestId;
    private String size;
    private int score;
    private int watchMode;
    private String authorId;
    private String nickname;
    private String avatar;
    private int showGold;
    private int gainGold;
    private String indexId;
    private String contentCategoryCode;
    private String contentCategoryName;
	
    
    //是否喜欢标识 0-是，1-否
    private int likeFlag =1; 
    //是否关注 0-是，1-否
    private int attendFlag =1;
    /**@用户信息集合（json格式：[{"userId":1, "nickName":"zhangsan"}]   ）**/
    private String noticeUserJson;
    /**关联话题集合（json格式：[{"id":1, "topicName":"话题1"}]  ）**/
    private String topicJson;
    
}
