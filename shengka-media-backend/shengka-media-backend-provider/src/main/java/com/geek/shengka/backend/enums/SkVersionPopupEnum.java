package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/1 14:36
 */
@Getter
public enum SkVersionPopupEnum {
    YES((byte) 1, "弹窗"),
    NO((byte) 2, "不弹窗");
    private Byte value;
    private String desc;

    SkVersionPopupEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte popup) {
        if (popup == null) {
            return false;
        }
        for (SkVersionPopupEnum skVersionStateEnum : SkVersionPopupEnum.values()) {
            if (skVersionStateEnum.getValue().intValue() == popup.intValue()) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(Byte value) {
        if (value == null) {
            return null;
        }
        for (SkVersionPopupEnum element : SkVersionPopupEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }
}
