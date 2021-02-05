package com.geek.shengka.content.entity.vo;

import com.geek.shengka.common.basemodel.BaseMediaInfo;
import com.geek.shengka.content.entity.SkTopicConfig;
import com.geek.shengka.content.entity.SkUserBaseInfo;
import lombok.Data;

import java.util.List;

/**
 * @Filename: SkCateMediaInfo
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/1 ;
 */
@Data
public class SkMediaInfoVO {
    private String id;
    /**视频的话题list*/
    private String topicJson;
    /**视频@好友list*/
    private String noticeUserJson;
 

}
