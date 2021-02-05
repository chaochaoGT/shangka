package com.geek.shengka.common.enums;

import lombok.Getter;

/**
 * Author: cuihuayang
 * Date: 2019/6/5 20:20
 * Description:
 * @author hc
 */
@Getter
public enum AccountTypeEnum {

    /**人民币现金*/
    RMB_CASH(100001, 0, "人民币现金"),
    /**虚拟货币金币*/
    VIRTUAL_CURRENCY_GOLD_COIN(200001, 1, "虚拟货币金币"),
    ;

    /**账务中心识别码*/
    private Integer accountType;
    /**前端传递的识别码*/
    private Integer code;
    /**识别码描述*/
    private String name;

    AccountTypeEnum(Integer accountType, Integer code, String name) {
        this.accountType = accountType;
        this.code = code;
        this.name = name;
    }

    /**
     * 获取对象
     *
     * @param code
     * @return
     */
    private static AccountTypeEnum getEnum(int code) {
        AccountTypeEnum accountTypeEnum = null;
        AccountTypeEnum[] dbEnums = AccountTypeEnum.values();
        for (int i = 0; i < dbEnums.length; i++) {
            if (dbEnums[i].getCode()==code) {
                accountTypeEnum = dbEnums[i];
                break;
            }
        }
        return accountTypeEnum;
    }

    /**
     * 获取getType
     *
     * @param code
     * @return
     */
    public static Integer getType(int code) {
        try {
            return AccountTypeEnum.getEnum(code).getAccountType();
        } catch (Exception e) {
            return null;
        }
    }

}
