package com.geek.shengka.content.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 观看视频上报入参
 *
 * @author: yunfei
 * @create: 2019-07-31 16:59
 **/
@Getter
@Setter
public class DelWatchHistoryRequest {

    /**用户Id*/
    private Long userId;

    /**视频id (全部删除业务 此处可不传)*/
    private List<String> videoId;

    /**全部删除业务以此做标识 1，全部删除，0选择删除*/
    @NotNull
    private Integer flag;

}
