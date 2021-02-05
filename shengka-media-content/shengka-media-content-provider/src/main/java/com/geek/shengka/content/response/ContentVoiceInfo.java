package com.geek.shengka.content.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class ContentVoiceInfo implements Serializable{
	private static final long serialVersionUID = -7109849181565054895L;
   
    private String audioId;
    /** 语音路径 **/
    private String url;
    /** 评论大小 */
    private String size;
    /** 作者名称 **/
    private String nickname;
    /** 作者图像 **/
    private String avatar;
    /** 视频id **/
    private String videoId;
    /** 作者 **/
    private long userId;
    /** 时长 **/
    private int duration;
    /** 地理位置 **/
    private String geographic;
    
}
