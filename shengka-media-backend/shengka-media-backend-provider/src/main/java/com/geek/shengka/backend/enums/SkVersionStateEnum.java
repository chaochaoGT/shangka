package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/1 14:36
 */
@Getter
public enum SkVersionStateEnum {
    FAILURE((byte) 0, "失效"),
    VALID((byte) 1, "有效"),
    DELETED((byte) 9, "已删除");
    private Byte value;
    private String desc;

    SkVersionStateEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte state) {
        if (state == null) {
            return false;
        }
        for (SkVersionStateEnum skVersionStateEnum : SkVersionStateEnum.values()) {
            if (skVersionStateEnum.getValue().intValue() == state.intValue()) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(Byte value) {
        if (value == null) {
            return null;
        }
        for (SkVersionStateEnum element : SkVersionStateEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }
}
