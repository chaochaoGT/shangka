package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qubianzhong
 * @Date 17:09 2019/8/2
 */
@Data
@ApiModel(value = "宝箱设置")
public class SkTreasureBoxConfig implements Serializable {
    private static final long serialVersionUID = 1746447193893193195L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 宝箱活动名称
     */
    @ApiModelProperty(value = "宝箱活动名称")
    private String boxName;

    /**
     * 活动简介
     */
    @ApiModelProperty(value = "活动简介")
    private String content;

    /**
     * 累计时长（单位：秒）
     */
    @ApiModelProperty(value = "累计时长（单位：秒）")
    private Integer watchDuration;

    /**
     * 宝箱金币下限
     */
    @ApiModelProperty(value = "宝箱金币下限")
    private Integer coinMin;

    /**
     * 宝箱金币上限
     */
    @ApiModelProperty(value = "宝箱金币上限")
    private Integer coinMax;

    /**
     * 图标url
     */
    @ApiModelProperty(value = "图标url")
    private String iconUrl;

    /**
     * 限制类型（1-次数限制   2-金币数限制）
     */
    @ApiModelProperty(value = "限制类型（1-次数限制   2-金币数限制）", allowableValues = "1,2")
    private Byte limitType;

    /**
     * 每日领取次数上限
     */
    @ApiModelProperty(value = "每日领取次数上限")
    private Integer limitCount;

    /**
     * 每日领取金币上限
     */
    @ApiModelProperty(value = "每日领取金币上限")
    private Integer limitCoinAmount;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 0-启用,1-禁用
     */
    @ApiModelProperty(value = "0-启用,1-禁用", allowableValues = "0,1")
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
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

}