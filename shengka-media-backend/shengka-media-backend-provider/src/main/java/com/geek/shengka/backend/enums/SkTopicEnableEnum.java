package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/13 9:27
 */
@Getter
public enum SkTopicEnableEnum {
    OPEN((byte) 0, "启用"),
    CLOSE((byte) 1, "禁用");
    private Byte value;
    private String desc;

    SkTopicEnableEnum(Byte type, String desc) {
        this.value = type;
        this.desc = desc;
    }

    public static Boolean isExist(Byte value) {
        if (value == null) {
            return false;
        }
        for (SkTopicEnableEnum element : SkTopicEnableEnum.values()) {
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
        for (SkTopicEnableEnum element : SkTopicEnableEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }}
