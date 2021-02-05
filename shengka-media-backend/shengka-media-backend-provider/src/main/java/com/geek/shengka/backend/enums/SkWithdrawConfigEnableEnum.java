package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/13 10:12
 */
@Getter
public enum SkWithdrawConfigEnableEnum {
    OPEN((byte) 0, "启用"),
    CLOSE((byte) 1, "禁用");
    private Byte value;
    private String desc;

    SkWithdrawConfigEnableEnum(Byte type, String desc) {
        this.value = type;
        this.desc = desc;
    }

    public static Boolean isExist(Byte value) {
        if (value == null) {
            return false;
        }
        for (SkWithdrawConfigEnableEnum element : SkWithdrawConfigEnableEnum.values()) {
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
        for (SkWithdrawConfigEnableEnum element : SkWithdrawConfigEnableEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }
}
