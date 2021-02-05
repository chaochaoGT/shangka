package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_topic_config
 *
 * @author
 */
@Data
@ApiModel(value = "话题配置")
public class SkTopicConfig implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 话题名称
     */
    @ApiModelProperty(value = "话题名称")
    private String topicName;

    /**
     * 话题简介
     */
    @ApiModelProperty(value = "话题简介")
    private String topicIntro;

    /**
     * 话题logo
     */
    @ApiModelProperty(value = "话题logo")
    private String topicLogo;

    /**
     * 发布视频数量
     */
    @ApiModelProperty(value = "发布视频数量")
    private Integer pushCount;

    /**
     * 话题类型（1-热门  2-推荐  3-新增）
     */
    @ApiModelProperty(value = "话题类型（1-热门  2-推荐  3-新增）")
    private Byte topicTag;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer seq;

    /**
     * 0-启用,1-禁用
     */
    @ApiModelProperty(value = "0-启用,1-禁用")
    private Byte enable;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    /**
     * (0-系统话题  1-用户自定义话题)
     */
    @ApiModelProperty(value = "(0-系统话题  1-用户自定义话题)")
    private Byte topicType;

    /**
     * 播放次数
     */
    @ApiModelProperty(value = "播放次数")
    private Integer watchCount;

}