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
@Data
@ApiModel(value = "榜单映射")
public class SkRankMapping implements Serializable {
    private static final long serialVersionUID = 179791687577595955L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 榜单id
     */
    @ApiModelProperty(value = "榜单id")
    private Long rankId;

    /**
     * 榜单类型（1-视频  2-用户  3-话题）
     */
    @ApiModelProperty(value = "榜单类型（1-视频  2-用户  3-话题）", allowableValues = "1,2,3")
    private Byte rankType;

    /**
     * 关联id（视频id/用户id/话题id）
     */
    @ApiModelProperty(value = "关联id（视频id/用户id/话题id）")
    private String relId;

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