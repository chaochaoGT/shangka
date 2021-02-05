package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/13 9:27
 */
@Getter
public enum SkTaskTypeEnum {
    DAY_TASK((byte) 1, "每日任务"),
    NOOB_TASK((byte) 2, "新手任务");
    private Byte value;
    private String desc;

    SkTaskTypeEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte value) {
        if (value == null) {
            return false;
        }
        for (SkTaskTypeEnum typeEnum : SkTaskTypeEnum.values()) {
            if (typeEnum.getValue().intValue() == value.intValue()) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(Byte value) {
        if (value == null) {
            return null;
        }
        for (SkTaskTypeEnum element : SkTaskTypeEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }}
