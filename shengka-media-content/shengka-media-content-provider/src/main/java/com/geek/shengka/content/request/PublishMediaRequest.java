package com.geek.shengka.content.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PublishMediaRequest {
    /**发布者id*/
    @NotNull
    private long publishUserId;
    /**标题*/
    @NotNull
	private String title;
    /**视频地址*/
    @NotNull
	private String videoUrl; 
    /**封面地址*/
    @NotNull
    private String coverImageUrl;
    /**话题ids集合*/
    private String topicIds;
    /**@粉丝id集合*/
    @NotNull
    private String noticeUserIds;
    /**是否公开0-公开，1-好友，2-私密*/
    @NotNull
    private byte conceal;
    @NotNull
    private String avatarUrl;
    @NotNull
    private String nickname;
    
	private String size;
	private String width;
	private String height;
	private String duration;
	private int watchMode;

	
	private String contentId;

	 
}
