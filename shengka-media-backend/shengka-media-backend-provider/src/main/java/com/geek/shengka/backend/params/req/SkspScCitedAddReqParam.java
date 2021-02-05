package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qubianzhong
 * @date 2019/8/29 13:09
 */
@Data
public class SkspScCitedAddReqParam implements Serializable {
    private static final long serialVersionUID = -5480839037753714969L;
    /**
     * 系统（1 Android，2  H5）
     */
    @ApiModelProperty(value = "系统（1 Android，2  H5）")
    private String osSystem;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private Date date;

    /**
     * 项目
     */
    @ApiModelProperty(value = "项目")
    private String app;

    /**
     * 渠道
     */
    @ApiModelProperty(value = "渠道")
    private String market;

    /**
     * 代理商
     */
    @ApiModelProperty(value = "代理商")
    private String agentAccount;

    /**
     * 渠道包名
     */
    @ApiModelProperty(value = "渠道包名")
    private String marketName;

    /**
     * 账面消耗
     */
    @ApiModelProperty(value = "账面消耗")
    private BigDecimal bookCac;

    /**
     * 现金消耗
     */
    @ApiModelProperty(value = "现金消耗")
    private BigDecimal cashCac;

    /**
     * 曝光
     */
    @ApiModelProperty(value = "曝光")
    private Long exposureCnt;

    /**
     * 点击数
     */
    @ApiModelProperty(value = "点击数")
    private Long clickCnt;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
}
