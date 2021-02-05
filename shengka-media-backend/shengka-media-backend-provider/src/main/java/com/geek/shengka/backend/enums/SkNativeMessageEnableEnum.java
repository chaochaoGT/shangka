package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/1 15:50
 */
@Getter
public enum SkNativeMessageEnableEnum {
    OPEN((byte) 0, "有效"),
    CLOSE((byte) 1, "无效");

    private Byte value;
    private String desc;

    SkNativeMessageEnableEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte enable) {
        if (enable == null) {
            return false;
        }
        for (SkNativeMessageEnableEnum skCategoryEnableEnum : SkNativeMessageEnableEnum.values()) {
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
        for (SkNativeMessageEnableEnum element : SkNativeMessageEnableEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }}
