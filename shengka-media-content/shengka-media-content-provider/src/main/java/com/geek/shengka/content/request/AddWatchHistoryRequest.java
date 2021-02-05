package com.geek.shengka.content.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 观看视频上报入参
 *
 * @author: yunfei
 * @create: 2019-07-31 16:59
 **/
@Getter
@Setter
public class AddWatchHistoryRequest {

    /**视频id*/
    @NotNull
    private String videoId;

    /**用户Id*/
    private Long userId;



}
