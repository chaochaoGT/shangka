package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:53
 */
@Data
@ApiModel(description = "话题新增")
public class SkTopicConfigAddReqParam implements Serializable {

    private static final long serialVersionUID = -7387354448108152944L;
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
     * 话题类型（1-热门  2-推荐  3-新增）
     */
    @ApiModelProperty(value = "话题类型（1-热门  2-推荐  3-新增）", allowableValues = "1,2,3")
    private Byte topicTag;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer seq;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 播放次数
     */
    @ApiModelProperty(value = "播放次数")
    private Integer watchCount;

    /**
     * 对应的视频列表
     */
    @ApiModelProperty(value = "对应的视频列表")
    private List<SkTopicVideoAddReqParam> topicVideos;
}
