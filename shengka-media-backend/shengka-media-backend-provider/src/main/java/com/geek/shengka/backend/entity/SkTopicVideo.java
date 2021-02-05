package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_topic_video
 *
 * @author
 */
@Data
@ApiModel(value = "话题视频关联")
public class SkTopicVideo implements Serializable {
    private static final long serialVersionUID = 2215298017309480946L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 话题id
     */
    @ApiModelProperty(value = "话题id")
    private Long topicId;

    /**
     * 视频id
     */
    @ApiModelProperty(value = "视频id")
    private String videoId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer seq;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}