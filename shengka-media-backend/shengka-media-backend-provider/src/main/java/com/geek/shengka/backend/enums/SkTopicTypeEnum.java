package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/13 9:27
 */
@Getter
public enum SkTopicTypeEnum {
    SYSTEM((byte) 0, "系统话题"),
    CUSTOM((byte) 1, "用户自定义话题");
    private Byte value;
    private String desc;

    SkTopicTypeEnum(Byte type, String desc) {
        this.value = type;
        this.desc = desc;
    }

    public static Boolean isExist(Byte value) {
        if (value == null) {
            return false;
        }
        for (SkTopicTypeEnum element : SkTopicTypeEnum.values()) {
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
        for (SkTopicTypeEnum element : SkTopicTypeEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }}
