package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/1 15:50
 */
@Getter
public enum SkRankEnableEnum {
    OPEN((byte) 0, "启用"),
    CLOSE((byte) 1, "禁用");

    private Byte value;
    private String desc;

    SkRankEnableEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte enable) {
        if (enable == null) {
            return false;
        }
        for (SkRankEnableEnum skRankEnableEnum : SkRankEnableEnum.values()) {
            if (skRankEnableEnum.getValue().intValue() == enable.intValue()) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(Byte value) {
        if (value == null) {
            return null;
        }
        for (SkRankEnableEnum element : SkRankEnableEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }}
