package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @Date 10:50 2019/8/2
 */
@ApiModel(value = "人气榜单")
@Data
public class SkRankList implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 榜单logo
     */
    @ApiModelProperty(value = "榜单logo")
    private String rankLogo;

    /**
     * 榜单Banner
     */
    @ApiModelProperty(value = "榜单Banner")
    private String rankBanner;

    /**
     * 榜单名称
     */
    @ApiModelProperty(value = "榜单名称")
    private String rankName;

    /**
     * 榜单规则
     */
    @ApiModelProperty(value = "榜单规则")
    private String rankRule;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer seq;

    /**
     * 0-启用,1-禁用
     */
    @ApiModelProperty(value = " 0-启用,1-禁用", allowableValues = "0,1")
    private Integer enable;

    /**
     * 榜单类型（1-视频  2-用户  3-话题）
     */
    @ApiModelProperty(value = "榜单类型（1-视频  2-用户  3-话题）", allowableValues = "1,2,3")
    private Byte rankType;

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

}