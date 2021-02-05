package com.geek.shengka.backend.params.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qubianzhong
 * @date 2019/8/26 10:39
 */
@Data
@ApiModel
public class SkModProfitUpdateReqParam implements Serializable {
    private static final long serialVersionUID = 2583963158187781191L;
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;
    /**
     * 系统 1：安卓，2：IOS，3：H5，默认为1
     */
    @ApiModelProperty(value = "系统 1：安卓，2：IOS，3：H5，默认为1")
    private Integer osSystem;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期,格式： yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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
     * 创建人
     */
    @ApiModelProperty(value = "创建人--不需要前端输入")
    private String updateBy;

}
