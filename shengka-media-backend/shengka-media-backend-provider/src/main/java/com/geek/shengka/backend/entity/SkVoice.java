package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class SkVoice implements Serializable {
    private static final long serialVersionUID = -5583106428928947523L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 视频id
     */
    @ApiModelProperty(value = "视频id")
    private String videoId;

    /**
     * 语音地址
     */
    @ApiModelProperty(value = "语音地址")
    private String voiceUrl;

    /**
     * 播放时长（单位：秒）
     */
    @ApiModelProperty(value = "播放时长（单位：秒）")
    private Integer duration;

    /**
     * 地理位置
     */
    @ApiModelProperty(value = "地理位置")
    private String geographic;

    /**
     * 0-审核中，1-审核通过， 2-审核失败，3-被删除
     */
    @ApiModelProperty(value = "0-审核中，1-审核通过， 2-审核失败，3-被删除")
    private Byte enable;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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

    /**
     * 经纬度
     */
    @ApiModelProperty(value = "经纬度")
    private Object position;

    /**
     * 用户来源（0-注册  1-系统导入）
     */
    @ApiModelProperty(value = "用户来源（0-注册  1-系统导入）")
    private Byte resource;

}