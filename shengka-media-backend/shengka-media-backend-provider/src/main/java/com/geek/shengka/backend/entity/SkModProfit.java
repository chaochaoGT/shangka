package com.geek.shengka.backend.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qubianzhong
 * @Date 14:25 2019/8/26
 */
@Data
public class SkModProfit implements Serializable {
    private static final long serialVersionUID = -1506617857178007027L;
    /**
     * ID
     */
    private Long id;

    /**
     * 系统 1：安卓，2：IOS，3：H5，默认为1
     */
    private Short osSystem;

    /**
     * 日期
     */
    private Date date;

    /**
     * 模块收入
     */
    private BigDecimal modProfit;

    /**
     * 收入类型
     */
    private String profitType;

    /**
     * 收入主体
     */
    private String profitMain;

    /**
     * 广告商ECPM
     */
    private BigDecimal adEcpm;

    /**
     * 订单数
     */
    private Long orders;

    /**
     * 成单数
     */
    private Long ordersSuc;

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