package com.geek.shengka.content.entity.vo;

import com.geek.shengka.content.entity.SkSearchPageConfig;
import lombok.Data;

import java.util.List;

/**
 * @Filename: SkSearchModelVO
 * @Description:
 * @Version: 1.0
 * @Author: wangchao
 * @Email: wangchao@hellogeek.com
 * @date: 2019/8/5 ;
 */
@Data
public class SkSearchModuleVO extends SkSearchPageConfig {
//    private Long id;
//    /**榜单图标*/
//    private String iconUrl;
//    /**榜单标识*/
//    private String moduleCode;
//    /**榜单简介*/
//    private String moduleDesc;
//    /**榜单名称*/
//    private String moduleName;
//    /**榜单banner*/
//    private String bannerUrl;
//    /**榜单排序*/
//    private Integer seq;
//    /**榜单类型（1-视频  2-话题）*/
//    private Integer moduleType;
//    /**榜单更新时间*/
//    private Date updateTime;
    /**榜单资源s*/
    private List<SkSearchSourceVO> sourceList;


    private String sourceIds;
}
