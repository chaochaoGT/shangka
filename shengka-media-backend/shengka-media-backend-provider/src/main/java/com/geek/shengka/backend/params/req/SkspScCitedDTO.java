package com.geek.shengka.backend.params.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * llsp_sc_cited
 * @author 
 */
@ApiModel
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkspScCitedDTO implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "id")
    private Long id;


    /**
     * 系统
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
    @ApiModelProperty(value = "日期")
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;


}