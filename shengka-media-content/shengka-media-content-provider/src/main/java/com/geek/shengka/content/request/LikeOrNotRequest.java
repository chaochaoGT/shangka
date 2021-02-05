package com.geek.shengka.content.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 视频举报入参
 *
 * @author: yunfei
 * @create: 2019-07-31 16:59
 **/
@Getter
@Setter
public class LikeOrNotRequest implements Serializable {

    /**视频id*/
    @NotNull
    private String videoId;

    /**0-喜欢  1-不喜欢*/
    @NotNull
    private String likeState;

    /**用户Id*/
    private Long userId;

    /**喜欢/不喜欢原因*/
    private String reason;

}
