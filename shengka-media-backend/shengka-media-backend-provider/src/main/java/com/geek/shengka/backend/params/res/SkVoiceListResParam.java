package com.geek.shengka.backend.params.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/21 14:50
 */
@Data
@ApiModel
public class SkVoiceListResParam implements Serializable {
    private static final long serialVersionUID = 3901127669834403463L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 视频id
     */
    @ApiModelProperty(value = "视频id")
    private String videoId;

    /**
     * 语音地址
     */
    @ApiModelProperty(value = "语音地址")
    private String voiceUrl;

    /**
     * 播放时长（单位：秒）
     */
    @ApiModelProperty(value = "播放时长（单位：秒）")
    private Integer duration;

    /**
     * 0-审核中，1-审核通过， 2-审核失败，3-被删除
     */
    @ApiModelProperty(value = "0-审核中，1-审核通过， 2-审核失败，3-被删除")
    private Byte enable;

    /**
     * 用户来源（0-注册  1-系统导入）
     */
    @ApiModelProperty(value = "用户来源（0-注册  1-系统导入）")
    private Byte resource;
}
