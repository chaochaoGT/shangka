package com.geek.shengka.backend.params.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qubianzhong
 * @date 2019/8/26 11:31
 */
@Data
public class SkModProfitResParam implements Serializable {
    private static final long serialVersionUID = 6533739743782273808L;
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 系统 1：安卓，2：IOS，3：H5，默认为1
     */
    @ApiModelProperty(value = "系统 1：安卓，2：IOS，3：H5，默认为1")
    private Short osSystem;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private Date date;

    /**
     * 模块收入
     */
    @ApiModelProperty(value = "模块收入")
    private BigDecimal modProfit;

    /**
     * 收入类型
     */
    @ApiModelProperty(value = "收入类型")
    private String profitType;

    /**
     * 收入主体
     */
    @ApiModelProperty(value = "收入主体")
    private String profitMain;

    /**
     * 广告商ECPM
     */
    @ApiModelProperty(value = "广告商ECPM")
    private BigDecimal adEcpm;

    /**
     * 订单数
     */
    @ApiModelProperty(value = "订单数")
    private Long orders;

    /**
     * 成单数
     */
    @ApiModelProperty(value = "成单数")
    private Long ordersSuc;

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
