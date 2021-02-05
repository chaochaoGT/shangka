package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_version
 *
 * @author
 */
@Data
@ApiModel(value = "版本")
public class SkVersion implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * APP类型：1-iPhone 2-iPad 3-Android 4-微信 5-H5
     */
    @ApiModelProperty(value = "APP类型：1-iPhone 2-iPad 3-Android 4-微信 5-H5")
    private Byte appType;

    /**
     * 0:常规更新     1：强制更新
     */
    @ApiModelProperty(value = "0:常规更新     1：强制更新")
    private Byte forcedUpdate;

    /**
     * 版本序号，递增
     */
    @ApiModelProperty(value = "版本序号，递增")
    private Integer versionCode;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private String versionNumber;

    /**
     * 下载地址
     */
    @ApiModelProperty(value = "下载地址")
    private String downloadUrl;

    /**
     * 状态: 0 失效  1 有效  9 已删除
     */
    @ApiModelProperty(value = "状态: 0 失效  1 有效  9 已删除")
    private Byte state;

    /**
     * 是否弹窗  1-弹窗   2-不弹窗
     */
    @ApiModelProperty(value = "是否弹窗  1-弹窗   2-不弹窗")
    private Byte popup;

    /**
     * 更新开始时间
     */
    @ApiModelProperty(value = "更新开始时间")
    private Date beginTime;

    /**
     * 更新结束时间
     */
    @ApiModelProperty(value = "更新结束时间")
    private Date endTime;

    /**
     * 产品类型
     */
    @ApiModelProperty(value = "产品类型")
    private String prdType;

    /**
     * 渠道id
     */
    @ApiModelProperty(value = "渠道id")
    private Long channelId;

    /**
     * 渠道名称
     */
    @ApiModelProperty(value = "渠道名称")
    private String channelName;

    /**
     * 渠道编号
     */
    @ApiModelProperty(value = "渠道编号")
    private String channelCode;

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
    /**
     * 更新描述
     */
    @ApiModelProperty(value = "更新描述")
    private String changeDesc;
    /**
     * 更新日志
     */
    @ApiModelProperty(value = "更新日志")
    private String changeLog;
}