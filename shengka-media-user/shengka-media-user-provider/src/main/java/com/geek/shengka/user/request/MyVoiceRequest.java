package com.geek.shengka.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 我的发声入参
 *
 * @author: yunfei
 * @create: 2019-07-31 16:59
 **/
@Getter
@Setter
public class MyVoiceRequest {

    /**用户Id*/
    private Long userId;

    /**每页多少条*/
    @NotNull
    private Integer pageSize;

    /**页码*/
    @NotNull
    private Integer pageNum;


}
