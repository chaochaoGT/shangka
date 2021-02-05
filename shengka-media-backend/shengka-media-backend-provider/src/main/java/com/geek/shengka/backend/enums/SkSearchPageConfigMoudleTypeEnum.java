package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/1 15:50
 */
@Getter
public enum SkSearchPageConfigMoudleTypeEnum {
    VIDEO((byte) 1, "视频"),
    TOPIC((byte) 2, "话题");

    private Byte value;
    private String desc;

    SkSearchPageConfigMoudleTypeEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte type) {
        if (type == null) {
            return false;
        }
        for (SkSearchPageConfigMoudleTypeEnum element : SkSearchPageConfigMoudleTypeEnum.values()) {
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
        for (SkSearchPageConfigMoudleTypeEnum element : SkSearchPageConfigMoudleTypeEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }}
