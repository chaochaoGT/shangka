package com.geek.shengka.backend.params.res;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 收支
 * jrl_sdsr
 *
 * @author
 */
@Data
public class SkSdsrListReqParam implements Serializable {
    private static final long serialVersionUID = -6641947497030270929L;
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

}