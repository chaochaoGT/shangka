package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/1 17:41
 */
@Data
@ApiModel(description = "话题与视频关系")
public class SkTopicVideoAddReqParam implements Serializable {
    private static final long serialVersionUID = -5099486054132604854L;
    /**
     * 话题id
     */
    @ApiModelProperty(value = "话题id")
    private Long topicId;

    /**
     * 视频id
     */
    @ApiModelProperty(value = "视频id")
    private String videoId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer seq;
}
