package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/1 15:50
 */
@Getter
public enum SkCategoryTypeEnum {
    DEFAULT((byte) 1, "默认"),
    CHOOSE((byte) 2, "可选");

    private Byte value;
    private String desc;

    SkCategoryTypeEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte type) {
        if (type == null) {
            return false;
        }
        for (SkCategoryTypeEnum skCategoryEnableEnum : SkCategoryTypeEnum.values()) {
            if (skCategoryEnableEnum.getValue().intValue() == type.intValue()) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(Byte value) {
        if (value == null) {
            return null;
        }
        for (SkCategoryTypeEnum element : SkCategoryTypeEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }}
