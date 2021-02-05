package com.geek.shengka.user.request;

import com.geek.shengka.common.basemodel.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 粉丝关注/不关注 入参
 *
 * @author: yunfei
 * @create: 2019-08-01 11:21
 **/
@Data
public class FansRequest extends PageRequest implements Serializable {

    /**
     * 用户
     */
    private Long userId;
    /**
     * 被关注用户
     */
    private Long attentionUserId;

    /**0-关注 1-取消关注*/
    private int attentionType;

    /**0关注，1粉丝*/
    private int userLabel;
    /**关键词*/
    private String keyWord;
}
