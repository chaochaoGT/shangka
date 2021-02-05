package com.geek.shengka.backend.params.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qubianzhong
 * @date 2019/8/29 16:44
 */
@Data
public class SklSdsrAddReqParam implements Serializable {
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
     * 创建人
     */
    private String createBy;
}
