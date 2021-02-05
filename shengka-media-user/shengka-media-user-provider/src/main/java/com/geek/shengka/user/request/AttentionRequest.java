package com.geek.shengka.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 粉丝关注/不关注 入参
 *
 * @author: yunfei
 * @create: 2019-08-01 11:21
 **/
@Getter
@Setter
public class AttentionRequest  implements Serializable {

    /**
     * 用户
     */
    private Long userId;

    /**
     * 被关注用户
     */
    @NotNull
    private Long attentionUserId;

    /**0-关注 1-取消关注*/
    @NotNull
    private Integer attentionType;
}
