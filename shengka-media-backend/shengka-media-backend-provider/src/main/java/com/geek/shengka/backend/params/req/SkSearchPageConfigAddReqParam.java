package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/8/5 10:33
 */
@Data
@ApiModel(description = "搜索页新增")
public class SkSearchPageConfigAddReqParam implements Serializable {
    private static final long serialVersionUID = -2001455940812209109L;
    /**
     * 模块名称
     */
    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    /**
     * 模块编号（hot_search：声咖热搜   popular_rank：人气榜单   hotest_video：最热视频   find_wonder：发现精彩  ）
     */
    @ApiModelProperty(value = "模块编号（hot_search：声咖热搜   popular_rank：人气榜单   hotest_video：最热视频   find_wonder：发现精彩  ）",
            allowableValues = "hot_search,popular_rank,hotest_video,find_wonder")
    private String moduleCode;

    /**
     * 类型（1-视频  2-话题）
     */
    @ApiModelProperty(value = "类型（1-视频  2-话题）", allowableValues = "1,2")
    private Byte moduleType;

    /**
     * 模块简介
     */
    @ApiModelProperty(value = "模块简介")
    private String moduleDesc;

    /**
     * banner图地址
     */
    @ApiModelProperty(value = "banner图地址")
    private String bannerUrl;

    /**
     * icon地址
     */
    @ApiModelProperty(value = "icon地址")
    private String iconUrl;

    /**
     * 0-有效  1-无效
     */
    @ApiModelProperty(value = "0-有效  1-无效", allowableValues = "0,1")
    private Byte enable;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer seq;


    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 模块映射
     */
    @ApiModelProperty(value = "模块映射")
    private List<SkSearchModuleMappingAddReqParam> moduleMappings;
}
