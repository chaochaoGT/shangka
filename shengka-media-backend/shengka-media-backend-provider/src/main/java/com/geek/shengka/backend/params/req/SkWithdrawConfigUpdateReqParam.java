package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:41
 */
@Data
@ApiModel(description = "提现配置修改")
public class SkWithdrawConfigUpdateReqParam implements Serializable {
    private static final long serialVersionUID = -4989100673190396986L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 提现金额
     */
    @ApiModelProperty(value = "提现金额")
    private BigDecimal withdrawAmount;

    /**
     * 提现类型（1-常规提现  2-活动提现）
     */
    @ApiModelProperty(value = "提现类型（1-常规提现  2-活动提现）", allowableValues = "1,2")
    private Byte withdrawType;

    /**
     * 状态（0-启用  1-禁用）
     */
    @ApiModelProperty(value = "状态（0-启用  1-禁用）", allowableValues = "0,1")
    private Byte enable;

    /**
     * 提现说明
     */
    @ApiModelProperty(value = "提现说明")
    private String withdrawIntro;

    /**
     * 活动提现解锁事件（1-连续登录天数  2-累计观看时长）
     */
    @ApiModelProperty(value = "活动提现解锁事件（1-连续登录天数  2-累计观看时长）", allowableValues = "1,2")
    private Byte unlockEvent;

    /**
     * 连续登录天数
     */
    @ApiModelProperty(value = "连续登录天数")
    private Integer unlockLoginDay;

    /**
     * 累计观看时长
     */
    @ApiModelProperty(value = "累计观看时长")
    private Integer unlockWatchTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
