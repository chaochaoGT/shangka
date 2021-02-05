package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qubianzhong
 * @date 2019/8/20 17:08
 */
@Data
@ApiModel
public class SkVoicePublishReqParam implements Serializable {
    private static final long serialVersionUID = 3926671411457877756L;

    /**
     * 视频id
     */
    @ApiModelProperty(value = "视频id")
    @NotNull
    private String videoId;
    /**
     * 语音地址
     */
    @ApiModelProperty(value = "视频id")
    @NotNull
    private String voiceUrl;
    /**
     * 播放时长（单位：秒）
     */
    @ApiModelProperty(value = "播放时长（单位：秒）")
    @NotNull
    private Integer duration;
    /**
     * 当前语音的用户ID
     */
    @ApiModelProperty(value = "当前语音的用户ID")
    @NotNull
    private Long userId;


    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间--前端不需要输入")
    private Date createTime;
}
