package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/1 15:50
 */
@Getter
public enum SkRankTypeEnum {
    VIDEO((byte) 1, "视频"),
    USER((byte) 2, "用户"),
    TOPIC((byte) 3, "话题");

    private Byte value;
    private String desc;

    SkRankTypeEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte value) {
        if (value == null) {
            return false;
        }
        for (SkRankTypeEnum element : SkRankTypeEnum.values()) {
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
        for (SkRankTypeEnum element : SkRankTypeEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }
}
