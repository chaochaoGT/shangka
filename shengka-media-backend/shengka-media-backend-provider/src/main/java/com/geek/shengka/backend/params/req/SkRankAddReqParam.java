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
@ApiModel(description = "榜单新增")
public class SkRankAddReqParam implements Serializable {
    private static final long serialVersionUID = -2050167721958317369L;
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
    private Byte enable;

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
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 榜单关联视频列表
     */
    @ApiModelProperty(value = "榜单关联视频列表")
    private List<SkRankMappingAddReqParam> rankMappings;
}
