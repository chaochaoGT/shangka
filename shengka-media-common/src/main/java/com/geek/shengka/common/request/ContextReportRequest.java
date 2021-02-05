package com.geek.shengka.common.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 内容操作上报入参
 *
 * @author: yunfei
 * @create: 2019-08-06 17:40
 **/
@Getter
@Setter
public class ContextReportRequest implements Serializable {
    /**行为事件类型 1点赞 2投诉/举报 。。*/
    private int type;

    /**备注*/
    private String remark;

    /**视频ID*/
    private String videoId;

    /**用户ID*/
    private Long userId;

    /**版本号*/
    private String version;
}
