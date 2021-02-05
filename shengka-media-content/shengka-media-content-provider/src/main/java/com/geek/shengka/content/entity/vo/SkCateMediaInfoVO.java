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
public class SkCateMediaInfoVO extends BaseMediaInfo {
    /**视频的话题list*/
    private List<SkTopicConfig> topicInfo;
    /**视频@好友list*/
    private List<SkUserBaseInfo> noticeUserinfo;
    /**是否喜欢 0 不喜欢 1喜欢*/
    private int likeFlag;
    /**是否关注 0未关注 1已关注*/
    private int attentionFlag;

}
