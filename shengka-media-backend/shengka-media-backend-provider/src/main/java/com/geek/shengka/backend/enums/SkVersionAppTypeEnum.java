package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/1 14:36
 */
@Getter
public enum SkVersionAppTypeEnum {
    IPHONE((byte) 1, "iPhone"),
    IPAD((byte) 2, "iPad"),
    ANDROID((byte) 3, "Android"),
    WECHAT((byte) 4, "微信"),
    H5((byte) 5, "h5");

    private Byte value;
    private String desc;

    SkVersionAppTypeEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte appType) {
        if (appType == null) {
            return false;
        }
        for (SkVersionAppTypeEnum skVersionStateEnum : SkVersionAppTypeEnum.values()) {
            if (skVersionStateEnum.getValue().intValue() == appType.intValue()) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(Byte value) {
        if (value == null) {
            return null;
        }
        for (SkVersionAppTypeEnum element : SkVersionAppTypeEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }
}
