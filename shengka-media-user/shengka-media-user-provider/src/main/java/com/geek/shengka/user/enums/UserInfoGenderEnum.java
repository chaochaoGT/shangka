package com.geek.shengka.user.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/13 18:06
 */
@Getter
public enum UserInfoGenderEnum {
    MAN(1, "男"),
    WOMAN(0, "男"),
    UN_UNKNOWN(2, "未知"),
    ;

    private Integer value;
    private String desc;

    UserInfoGenderEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(Integer enable) {
        if (enable == null) {
            return false;
        }
        for (UserInfoGenderEnum element : UserInfoGenderEnum.values()) {
            if (element.getValue().intValue() == enable) {
                return true;
            }
        }
        return false;
    }}
