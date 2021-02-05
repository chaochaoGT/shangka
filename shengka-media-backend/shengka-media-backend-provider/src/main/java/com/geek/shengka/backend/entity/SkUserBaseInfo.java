package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
public class SkUserBaseInfo implements Serializable {
    private static final long serialVersionUID = -3556274632704437377L;
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
     * 声咖号
     */
    @ApiModelProperty(value = "声咖号")
    private String skCode;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    private String remark;

    /**
     * 背景图片
     */
    @ApiModelProperty(value = "背景图片")
    private String background;

    /**
     * 获赞数量
     */
    @ApiModelProperty(value = "获赞数量")
    private Integer thumbsNum;

    /**
     * 订阅话题数量
     */
    @ApiModelProperty(value = "订阅话题数量")
    private Integer subscribeTopicNum;

    /**
     * 发布作品数量
     */
    @ApiModelProperty(value = "发布作品数量")
    private Integer publishNum;

    /**
     * 发声数量
     */
    @ApiModelProperty(value = "发声数量")
    private Integer voiceNum;

    /**
     * 粉丝数量
     */
    @ApiModelProperty(value = "粉丝数量")
    private Integer fansNum;

    /**
     * 被喜欢数量
     */
    @ApiModelProperty(value = "被喜欢数量")
    private Integer likedNum;

    /**
     * 最近浏览视频数量
     */
    @ApiModelProperty(value = "最近浏览视频数量")
    private Integer nearestNum;

    /**
     * 性别，1：男，0：女，2：未知
     */
    @ApiModelProperty(value = "性别，1：男，0：女，2：未知")
    private Short gender;

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
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String userIcon;

    /**
     * 我关注的用户数量
     */
    @ApiModelProperty(value = "我关注的用户数量")
    private Integer attentionNum;

    /**
     * 我喜欢的作品数量
     */
    @ApiModelProperty(value = "我喜欢的作品数量")
    private Integer likeWorksNum;

    /**
     * 用户来源（0-注册  1-系统导入）
     */
    @ApiModelProperty(value = "用户来源（0-注册  1-系统导入）")
    private Byte resource;

}