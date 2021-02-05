package com.geek.shengka.common.enums;

import lombok.Getter;

/**
 * 举报类型枚举
 *
 * @author: yunfei
 * @create: 2019-07-31 17:34
 **/
@Getter
public enum ReportTypeEnum {

    video(1, "视频"),
    user(2, "用户"),

    ;


    private int code;
    private String desc;

    ReportTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
