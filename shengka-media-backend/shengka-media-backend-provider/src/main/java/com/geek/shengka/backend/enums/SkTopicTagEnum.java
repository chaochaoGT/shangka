package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/13 9:27
 */
@Getter
public enum SkTopicTagEnum {
    HOT((byte) 1, "热门"),
    RECOMMEND((byte) 2, "推荐"),
    ADD((byte) 3, "新增");
    private Byte value;
    private String desc;

    SkTopicTagEnum(Byte type, String desc) {
        this.value = type;
        this.desc = desc;
    }

    public static Boolean isExist(Byte value) {
        if (value == null) {
            return false;
        }
        for (SkTopicTagEnum element : SkTopicTagEnum.values()) {
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
        for (SkTopicTagEnum element : SkTopicTagEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }}
