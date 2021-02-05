package com.geek.shengka.content.response;

import lombok.Getter;
import lombok.Setter;


/**
 * 系统话题返回对象
 *
 * @author: yunfei
 * @create: 2019-07-31 18:45
 **/
@Getter
@Setter
public class TopicConfigResponse {

    /**
     * 话题id
     */
    private Long topicId;

    /**
     * 话题名称
     */
    private String topicName;

    /**
     * 话题logo
     */
    private String topicLogo;

}
