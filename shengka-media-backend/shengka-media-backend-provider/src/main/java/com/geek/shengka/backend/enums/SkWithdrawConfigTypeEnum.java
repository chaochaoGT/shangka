package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/13 10:12
 */
@Getter
public enum SkWithdrawConfigTypeEnum {
    CONVENTIONAL((byte) 1, "常规提现"),
    ACTIVITY((byte) 2, "活动提现");
    private Byte value;
    private String desc;

    SkWithdrawConfigTypeEnum(Byte type, String desc) {
        this.value = type;
        this.desc = desc;
    }

    public static Boolean isExist(Byte value) {
        if (value == null) {
            return false;
        }
        for (SkWithdrawConfigTypeEnum element : SkWithdrawConfigTypeEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(Byte value) {
        if (value == null) {
            return null;
        }
        for (SkWithdrawConfigTypeEnum element : SkWithdrawConfigTypeEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }
}
