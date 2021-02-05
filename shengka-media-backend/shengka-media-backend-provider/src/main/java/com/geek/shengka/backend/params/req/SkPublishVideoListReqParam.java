package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/20 15:49
 */
@Data
@ApiModel
public class SkPublishVideoListReqParam extends BasePageReqParam implements Serializable {
    private static final long serialVersionUID = -936968079005296497L;


    /**
     * 视频的标题
     */
    @ApiModelProperty(value = "视频的标题")
    private String title;
    /**
     * 视频的ID
     */
    @ApiModelProperty(value = "视频的ID")
    private String id;

    @ApiModelProperty(value = "来源（0-用户上传  1-系统导入）")
    private Integer resource;
}
