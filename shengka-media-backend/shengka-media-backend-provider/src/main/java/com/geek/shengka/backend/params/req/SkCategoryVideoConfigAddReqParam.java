package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/22 13:47
 */
@Data
@ApiModel
public class SkCategoryVideoConfigAddReqParam implements Serializable {
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

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人--不需要前端输入")
    private String createBy;
}
