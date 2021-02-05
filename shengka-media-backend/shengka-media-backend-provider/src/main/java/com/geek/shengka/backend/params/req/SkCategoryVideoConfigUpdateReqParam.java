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
public class SkCategoryVideoConfigUpdateReqParam implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 视频id
     */
    @ApiModelProperty(value = "视频id")
    private String videoId;

    /**
     * 0-启用，1-禁用
     */
    @ApiModelProperty(value = "0-启用，1-禁用")
    private Byte enable;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer seq;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人--不需要前端输入")
    private String updateBy;
}
