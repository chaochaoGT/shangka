package com.geek.shengka.user.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 黑名单
 *
 * @author: xuxuelei
 * @create: 2019-08-01 11:21
 **/
@Getter
@Setter
public class BlackRequest implements Serializable {

 
    /** * 被关注用户 */
    @NotNull
    private Long defriendUserId;
    @NotNull
    private long backId;
 
}
