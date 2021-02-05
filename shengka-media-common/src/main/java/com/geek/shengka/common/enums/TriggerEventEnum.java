package com.geek.shengka.common.enums;

import lombok.Getter;

/**
 * 任务触发事件枚举
 * 触发事件（1-发布视频  2-语音评论  3-关注用户  4-登录签到   5-首发语音评论）
 *
 * @author: yunfei
 * @create: 2019-07-31 17:34
 **/
@Getter
public enum TriggerEventEnum {

    /**语音评论*/
    status_goComment(2, 2, "去评论"),

    /**发布视频*/
    status_goUpload(1, 3, "去上传"),

    /**关注用户*/
    status_goAttention(3, 4, "去关注"),
    ;


    /**数据库triggerEvent的值*/
    private int code;
    /**转化传递到前台的状态值*/
    private int value;
    /**状态值的中文描述*/
    private String desc;

    TriggerEventEnum(int code, int value, String desc) {
        this.value = value;
        this.code = code;
        this.desc = desc;
    }


    /**
     * 获取对象
     *
     * @param code
     * @return
     */
    private static TriggerEventEnum getEnum(int code) {
        TriggerEventEnum baseTypeEnum = null;
        TriggerEventEnum[] dbEnums = TriggerEventEnum.values();
        for (int i = 0; i < dbEnums.length; i++) {
            if (dbEnums[i].getCode()==code) {
                baseTypeEnum = dbEnums[i];
                break;
            }
        }
        return baseTypeEnum;
    }

    /**
     * 获取 getValue
     *
     * @param code
     * @return
     */
    public static int getValue(int code) {
        try {
            return TriggerEventEnum.getEnum(code).getValue();
        } catch (Exception e) {
            return 0;
        }
    }
}
