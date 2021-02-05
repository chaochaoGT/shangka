package com.geek.shengka.common.enums;

import lombok.Getter;

/**
 * @Filename: UserActionEvent
 * @Description: 用户行为事件
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/6/4 ;
 */
@Getter
public enum UserActionEventEnum {

    like("like","点赞",1),
    report("report","投诉/举报",2),
    dislike("dislike","点衰",3),
    collect("collect","收藏",4),
    disCollect("dis_collect","取消收藏",5),
    play_end("play_end","播放结束",7),
    share("share","分享",8),
    comment("comment","评论",9),
    play_start("play","播放视频",10),
    ;


    private String eventId;
    private String eventName;
    private int type;


    UserActionEventEnum(String eventId , String eventName , int type) {
        this.eventId=eventId;
        this.eventName=eventName;
        this.type=type;
    }

    /**
     * 获取对象
     *
     * @param code
     * @return
     */
    private static UserActionEventEnum getEnum(int code) {
        UserActionEventEnum eventEnum = null;
        UserActionEventEnum[] dbEnums = UserActionEventEnum.values();
        for (int i = 0; i < dbEnums.length; i++) {
            if (dbEnums[i].getType()==code) {
                eventEnum = dbEnums[i];
                break;
            }
        }
        return eventEnum;
    }

    /**
     * 获取 getEventId
     *
     * @param type
     * @return
     */
    public static String getEventId(int type) {
        try {
            return UserActionEventEnum.getEnum(type).getEventId();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取 getEventName
     *
     * @param type
     * @return
     */
    public static String getEventName(int type) {
        try {
            return UserActionEventEnum.getEnum(type).getEventName();
        } catch (Exception e) {
            return null;
        }
    }

}
