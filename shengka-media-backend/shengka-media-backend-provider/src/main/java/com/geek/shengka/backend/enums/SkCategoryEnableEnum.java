package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/1 15:50
 */
@Getter
public enum SkCategoryEnableEnum {
    DISABLE((byte) 0, "禁用"),
    ENABLED((byte) 1, "启用");

    private Byte value;
    private String desc;

    SkCategoryEnableEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte enable) {
        if (enable == null) {
            return false;
        }
        for (SkCategoryEnableEnum skCategoryEnableEnum : SkCategoryEnableEnum.values()) {
            if (skCategoryEnableEnum.getValue().intValue() == enable.intValue()) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(Byte value) {
        if (value == null) {
            return null;
        }
        for (SkCategoryEnableEnum element : SkCategoryEnableEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }}
