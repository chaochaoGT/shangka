package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/1 15:50
 */
@Getter
public enum SkSearchPageConfigTypeEnum {
    VIDEO((byte) 1, "视频"),
    TOPIC((byte) 2, "话题");

    private Byte value;
    private String desc;

    SkSearchPageConfigTypeEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte type) {
        if (type == null) {
            return false;
        }
        for (SkSearchPageConfigTypeEnum element : SkSearchPageConfigTypeEnum.values()) {
            if (element.getValue().intValue() == type.intValue()) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(Byte value) {
        if (value == null) {
            return null;
        }
        for (SkSearchPageConfigTypeEnum element : SkSearchPageConfigTypeEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }

}
