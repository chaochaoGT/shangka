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
 * @date 2019/8/2 17:41
 */
@Data
@ApiModel(description = "宝箱配置修改")
public class SkTreasureBoxConfigUpdateReqParam implements Serializable {
    private static final long serialVersionUID = 5303796331647390336L;
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
    @JsonFormat(pattern = CommonConstant.DATE_TIME_FORMAT, timezone = CommonConstant.DATE_TIME_TIMEZONE)
    @DateTimeFormat(pattern = CommonConstant.DATE_TIME_FORMAT)
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = CommonConstant.DATE_TIME_FORMAT, timezone = CommonConstant.DATE_TIME_TIMEZONE)
    @DateTimeFormat(pattern = CommonConstant.DATE_TIME_FORMAT)
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
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
