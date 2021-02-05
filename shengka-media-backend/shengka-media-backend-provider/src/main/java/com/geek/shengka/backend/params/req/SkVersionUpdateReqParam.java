package com.geek.shengka.backend.params.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.geek.shengka.backend.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:53
 */
@Data
@ApiModel(description = "版本更新")
public class SkVersionUpdateReqParam implements Serializable {
    private static final long serialVersionUID = -6019975446344304833L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * APP类型：1-iPhone 2-iPad 3-Android 4-微信 5-H5
     */
    @ApiModelProperty(value = "APP类型：1-iPhone 2-iPad 3-Android 4-微信 5-H5", allowableValues = "1,2,3,4,5")
    private Byte appType;

    /**
     * 0:常规更新     1：强制更新
     */
    @ApiModelProperty(value = "0:常规更新     1：强制更新", allowableValues = "0,1")
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
    @ApiModelProperty(value = "状态: 0 失效  1 有效  9 已删除", allowableValues = "0,1,9")
    private Byte state;

    /**
     * 是否弹窗  1-弹窗   2-不弹窗
     */
    @ApiModelProperty(value = "是否弹窗  1-弹窗   2-不弹窗", allowableValues = "1,2")
    private Byte popup;

    /**
     * 更新开始时间
     */
    @ApiModelProperty(value = "更新开始时间")
    @DateTimeFormat(pattern = CommonConstant.DATE_TIME_FORMAT)
    @JsonFormat(pattern = CommonConstant.DATE_TIME_FORMAT, timezone = CommonConstant.DATE_TIME_TIMEZONE)
    private Date beginTime;

    /**
     * 更新结束时间
     */
    @ApiModelProperty(value = "更新结束时间")
    @DateTimeFormat(pattern = CommonConstant.DATE_TIME_FORMAT)
    @JsonFormat(pattern = CommonConstant.DATE_TIME_FORMAT, timezone = CommonConstant.DATE_TIME_TIMEZONE)
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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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
