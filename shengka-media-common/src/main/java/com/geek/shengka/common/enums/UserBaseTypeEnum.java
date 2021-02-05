package com.geek.shengka.common.enums;

import lombok.Getter;

/**
 * 用户参数类型枚举
 *
 * @author: yunfei
 * @create: 2019-07-31 17:34
 **/
@Getter
public enum UserBaseTypeEnum {

    /**获赞数量*/
    THUMBS_NUM(1, "thumbsNum", "获赞数量"),

    /**订阅话题数量*/
    SUBSCRIBE_TOPIC_NUM(2, "subscribeTopicNum", "订阅话题数量"),

    /**发布作品数量*/
    PUBLISH_NUM(3, "publishNum", "发布作品数量"),

    /**发声数量*/
    VOICE_NUM(4, "voiceNum", "发声数量"),

    /**粉丝数量*/
    FANS_NUM(5, "fansNum", "粉丝数量"),

    /**被喜欢数量*/
    LIKED_NUM(6, "likedNum", "被喜欢数量"),

    /**最近浏览视频数量*/
    NEAREST_NUM(7, "nearestNum", "最近浏览视频数量"),

    /**我关注的用户数量*/
    ATTENTION_NUM(8, "attentionNum", "我关注的用户数量"),

    /**我喜欢的作品数量*/
    LIKE_WORKS_NUM(9, "likeWorksNum", "我喜欢的作品数量"),

    ;


    /**事件标识码*/
    private int code;
    /**要更新的字段*/
    private String value;
    /**字段中文描述*/
    private String desc;

    UserBaseTypeEnum(int code, String value, String desc) {
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
    private static UserBaseTypeEnum getEnum(int code) {
        UserBaseTypeEnum baseTypeEnum = null;
        UserBaseTypeEnum[] dbEnums = UserBaseTypeEnum.values();
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
    public static String getValue(int code) {
        try {
            return UserBaseTypeEnum.getEnum(code).getValue();
        } catch (Exception e) {
            return null;
        }
    }
}
