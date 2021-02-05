package com.geek.shengka.content.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class VoiceInfo implements Serializable{

	private static final long serialVersionUID = -7109849181565054895L;
	   
    /** 语音ID **/
    private String audioId;
    /** 语音路径 **/
    private String url;
    /** 视频id **/
    private String videoId;
    /** 作者 **/
    private long userId;
    /** 时长 **/
    private int duration;
    /** 地理位置 **/
    private String geographic;
    /** 作者名称 **/
    private String nickname;
    /** 作者图像 **/
    private String avatar;
    
    
    /** 是否自己0-是，1-否 **/
    private int ownFlag =1;
    /** 是否关注0-是，1否 **/
    private int attendFlag =1;
    /** 是否点赞0-是，1否 **/
    private int thumbsUpFlag=1;
    
}
