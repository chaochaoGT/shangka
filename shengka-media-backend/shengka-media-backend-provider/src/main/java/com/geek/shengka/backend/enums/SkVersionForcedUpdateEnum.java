package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/1 14:36
 */
@Getter
public enum SkVersionForcedUpdateEnum {
    NORMAL((byte) 0, "常规更新"),
    FORCE((byte) 1, "强制更新");

    private Byte value;
    private String desc;

    SkVersionForcedUpdateEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte forcedUpdate) {
        if (forcedUpdate == null) {
            return false;
        }
        for (SkVersionForcedUpdateEnum skVersionStateEnum : SkVersionForcedUpdateEnum.values()) {
            if (skVersionStateEnum.getValue().intValue() == forcedUpdate.intValue()) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(Byte value) {
        if (value == null) {
            return null;
        }
        for (SkVersionForcedUpdateEnum element : SkVersionForcedUpdateEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }
}
