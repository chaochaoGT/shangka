package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/13 10:12
 */
@Getter
public enum SkWithdrawConfigUnlockEventEnum {
    CONSECUTIVE_LOGIN_DAYS((byte) 1, "连续登录天数"),
    CUMULATIVE_VIEWING_TIME((byte) 2, "累计观看时长");
    private Byte value;
    private String desc;

    SkWithdrawConfigUnlockEventEnum(Byte type, String desc) {
        this.value = type;
        this.desc = desc;
    }

    public static Boolean isExist(Byte value) {
        if (value == null) {
            return false;
        }
        for (SkWithdrawConfigUnlockEventEnum element : SkWithdrawConfigUnlockEventEnum.values()) {
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
        for (SkWithdrawConfigUnlockEventEnum element : SkWithdrawConfigUnlockEventEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }
}
