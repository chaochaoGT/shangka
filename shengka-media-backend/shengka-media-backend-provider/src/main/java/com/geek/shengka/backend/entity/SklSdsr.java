package com.geek.shengka.backend.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * jrl_sdsr
 *
 * @author
 */
@Data
public class SklSdsr implements Serializable {
    private static final long serialVersionUID = 7180340180715769576L;
    /**
     * 主键id
     */
    private Long id;

    private String osSystem;

    /**
     * 日期
     */
    private Date date;

    /**
     * 市场成本
     */
    private BigDecimal cac;

    /**
     * 日收入
     */
    private BigDecimal profit;

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