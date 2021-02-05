package com.geek.shengka.content.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 观看历史返回对象
 *
 * @author: yunfei
 * @create: 2019-07-31 18:45
 **/
@Getter
@Setter
public class WatchHistoryResponse {

    /**
     * 视频Id
     */
    private String vedioId;

    /**
     * 视频url
     */
    private String vedioUrl;

    /**
     * 封面url
     */
    private String coverImage;


    /**
     * 喜欢次数
     */
    private String likeNum;

}
