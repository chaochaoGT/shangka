package com.geek.shengka.content.enums;

import lombok.Getter;

/**
 * 视频喜欢/不喜欢枚举类
 *
 * @author: yunfei
 * @create: 2019-07-31 18:07
 **/
@Getter
public enum  VideoLikeOrNotEnum {

    /**喜欢*/
    LIKE("0","喜欢"),
    /**不喜欢*/
    NOT_LIKE("1","不喜欢"),
    ;

    VideoLikeOrNotEnum(String code,String desc){
        this.code=code;
        this.desc=desc;
    }
    private String code;
    private String desc;
}
