package com.geek.shengka.common.enums;

import lombok.Getter;

/***
 *
 * 账务中心TradeTypeCode编码
 * @author yunfei
 */
@Getter
public enum TradeTypeEnum {

    /**提现*/
    WITHDRAW(100001,  "提现"),
    /**开宝箱*/
    OPEN_TREASURE(1006,  "看视频获取金币"),
    NEW_TASK(1007,  "新手任务获取金币"),
    DAILY_TASK(1008,  "每日任务获取金币"),
    PUBLISH_VOICE_TASK(1009,  "语音评论"),
    PUBLISH_VIDEO_TASK(10011,  "上传视频"),
    FIRST_PUBLISH_VOICE_TASK(10012,  "视频首发语音评论"),
    ATTENT_TASK(10010,  "关注"),
    LOGIN_TASK(10013,  "登录签到"),
    ;

    private Integer code;
    private String desc;

    TradeTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取对象
     *
     * @param code
     * @return
     */
    private static TradeTypeEnum getEnum(int code) {
        TradeTypeEnum accountTypeEnum = null;
        TradeTypeEnum[] dbEnums = TradeTypeEnum.values();
        for (int i = 0; i < dbEnums.length; i++) {
            if (dbEnums[i].getCode()==code) {
                accountTypeEnum = dbEnums[i];
                break;
            }
        }
        return accountTypeEnum;
    }

    /**
     * 获取getDesc
     *
     * @param code
     * @return
     */
    public static String getType(int code) {
        try {
            return TradeTypeEnum.getEnum(code).getDesc();
        } catch (Exception e) {
            return null;
        }
    }

}
