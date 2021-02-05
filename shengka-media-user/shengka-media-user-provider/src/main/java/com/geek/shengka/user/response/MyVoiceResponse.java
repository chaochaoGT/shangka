package com.geek.shengka.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 我的发声返回对象
 *
 * @author: yunfei
 * @create: 2019-08-01 11:50
 **/
@Getter
@Setter
public class MyVoiceResponse implements Serializable {


    /**发布时间*/
    private Date creatTime;

    /**声音url*/
    private String voiceUrl;

    /**视频封面url*/
    private String coverImage;

    /**视频ID*/
    private String videoId;

    /**声音Id*/
    private String voiceId;

    /**
     * 播放时长（单位：秒）
     */
    private Integer duration;

    /**标题 埋点需要*/
    private String title;

    /**categoryId 埋点需要*/
    private Long categoryId;


}
