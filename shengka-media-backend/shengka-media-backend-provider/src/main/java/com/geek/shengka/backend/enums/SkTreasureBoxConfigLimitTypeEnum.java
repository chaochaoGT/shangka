package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/12 17:11
 */
@Getter
public enum SkTreasureBoxConfigLimitTypeEnum {
    NUMBER((byte) 1, "次数限制"),
    GOLD((byte) 2, "金币限制");
    private Byte value;
    private String desc;

    SkTreasureBoxConfigLimitTypeEnum(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Byte enable) {
        if (enable == null) {
            return false;
        }
        for (SkTreasureBoxConfigLimitTypeEnum configEnableEnum : SkTreasureBoxConfigLimitTypeEnum.values()) {
            if (configEnableEnum.getValue().intValue() == enable.intValue()) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(Byte value) {
        if (value == null) {
            return null;
        }
        for (SkTreasureBoxConfigLimitTypeEnum element : SkTreasureBoxConfigLimitTypeEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }}
