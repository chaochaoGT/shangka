package com.geek.shengka.gold.response;

import lombok.Data;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class UserAccountParam {

    private BigInteger id; //主键
    private String accountCode; //账户code
    private String userId; //用户id
    private String bizCode; //业务线：1：老王视频
    private Integer accountType; //账户类型
    private BigDecimal totalAmount; //总额度
    private BigDecimal usableAmount; //可用额度
    private BigDecimal freezeAmount; //冻结额度
    private Date createTime; //创建时间
    private Date updateTime; //修改时间
    private String createMan; //创建人
    private String updateMan; //修改人
    private Integer states; //账户状态
    private Integer page;
    private Integer rows;
    private List<Integer> accountTypeList;
    private String phone;
    private BigDecimal minUsableAmount;
    private Integer minId;
    private Date minCreateTime;
    
    private String idCard;
    private BigDecimal taxExemptionAmount;
}
