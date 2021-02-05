package com.geek.shengka.backend.enums;

import lombok.Getter;

/**
 * @author qubianzhong
 * @date 2019/8/13 9:27
 */
@Getter
public enum SkTaskTriggerEventEnum {
    PUSH_VIDEO((byte) 1, "发布视频"),
    VIOCE_COMMENT((byte) 2, "语音评论"),
    FOCUS_USER((byte) 3, "关注用户"),
    LOGIN_SIGN((byte) 4, "登录签到"),
    FIRST_VIOCE_COMMENT((byte) 5, "首发语音评论");
    private Byte value;
    private String desc;

    SkTaskTriggerEventEnum(Byte type, String desc) {
        this.value = type;
        this.desc = desc;
    }

    public static Boolean isExist(Byte value) {
        if (value == null) {
            return false;
        }
        for (SkTaskTriggerEventEnum element : SkTaskTriggerEventEnum.values()) {
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
        for (SkTaskTriggerEventEnum element : SkTaskTriggerEventEnum.values()) {
            if (element.getValue().intValue() == value.intValue()) {
                return element.getDesc();
            }
        }
        return null;
    }}
