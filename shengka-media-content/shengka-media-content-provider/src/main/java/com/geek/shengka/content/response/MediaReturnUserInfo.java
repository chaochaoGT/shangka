package com.geek.shengka.content.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class MediaReturnUserInfo implements Serializable{
	private static final long serialVersionUID = -7109849181565054895L;
 
    /** 作者 **/
    private long userId;
    /** 作者名称 **/
    private String nickname;
    /** 作者图像 **/
    private String avatar;
    
}
