package com.geek.shengka.backend.enums;

import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author qubianzhong
 * @date 2019/8/1 15:50
 */
@Getter
public enum SkSearchPageConfigCodeEnum {
    HOT_SEARCH("hot_search", "声咖热搜"),
    POPULAR_RANK("popular_rank", "人气榜单"),
    HOTEST_VIDEO("hotest_video", "最热视频"),
    FIND_WONDER("find_wonder", "发现精彩");

    private String value;
    private String desc;

    SkSearchPageConfigCodeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Boolean isExist(String code) {
        if (StringUtils.isEmpty(code)) {
            return false;
        }
        for (SkSearchPageConfigCodeEnum configCodeEnum : SkSearchPageConfigCodeEnum.values()) {
            if (configCodeEnum.getValue().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(String value) {
        if (value == null) {
            return null;
        }
        for (SkSearchPageConfigCodeEnum element : SkSearchPageConfigCodeEnum.values()) {
            if (element.getValue().equals(value)) {
                return element.getDesc();
            }
        }
        return null;
    }}
