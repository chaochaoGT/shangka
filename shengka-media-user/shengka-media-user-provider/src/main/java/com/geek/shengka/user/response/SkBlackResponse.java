package com.geek.shengka.user.response;

import java.io.Serializable;
import lombok.Data;

/**
 * sk_black
 * @author 
 */
@Data
public class SkBlackResponse implements Serializable {
	private static final long serialVersionUID = 2301946982327435002L;
	/** 黑名单ID**/
    private Long backId;
    private Long userId;
    private long blackUserId;
    private String remark;
    private String  nickname;
    private String userAvatar;


   
}