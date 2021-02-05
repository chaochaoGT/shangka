package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/13 9:27
 */
@Getter
public enum SkTaskAwardAmountTypeEnum {
    FIXED((byte) 1, "固定额度"),
    RANDOM((byte) 2, "随机额度");
    private Byte value;
    private String desc;

    SkTaskAwardAmountTypeEnum(Byte type, String desc) {
        this.value = type;
        this.desc = desc;
    }

    public static Boolean isExist(Byte value) {
        if (value == null) {
            return false;
        }
        for (SkTaskAwardAmountTypeEnum element : SkTaskAwardAmountTypeEnum.values()) {
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
        for (SkTaskAwardAmountTypeEnum element : SkTaskAwardAmountTypeEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }}
