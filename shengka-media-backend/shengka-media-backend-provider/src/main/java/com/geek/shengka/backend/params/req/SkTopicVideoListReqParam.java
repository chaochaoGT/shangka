package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:34
 */
@Data
@ApiModel(description = "话题视频")
public class SkTopicVideoListReqParam extends BasePageReqParam implements Serializable {

    private static final long serialVersionUID = -8507891191543341059L;
    @ApiModelProperty(value = "话题ID")
    private Long topicId;

}
