package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * sk_publish_video
 * @author 
 */
@Data
public class SkPublishVideo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 主键
     */
    private String id;

    /**
     * 发布者id
     */
    private Long publishUserId;

    /**
     * 标题
     */
    private String title;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 封面地址
     */
    private String coverImageUrl;

    /**
     * 谁可以看(0-公开  1-好友   2-私密)
     */
    private Byte conceal;

    /**
     * @用户id（多个id以“,”隔开）
     */
    private String noticeUserIds;

    /**
     * 话题id（多个id以“,”隔开）
     */
    private String topicIds;

    /**
     * 0-审核中，1-审核通过， 2-审核失败
     */
    private Byte enable;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**@用户信息集合（json格式：[{"userId":1, "nickName":"zhangsan"}]   ）**/
    private String noticeUserJson;
    /**关联话题集合（json格式：[{"id":1, "topicName":"话题1"}]  ）**/
    private String topicJson;

    /**
     * 观看方式：0，横屏1，竖屏
     */
    private Byte watchMode;

    /**
     * 内容中心类别id
     */
    private Long conCategoryId;
 
}