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
@ApiModel(description = "话题配置")
public class SkTopicConfigListReqParam extends BasePageReqParam implements Serializable {

    private static final long serialVersionUID = -7743389405242239018L;
    @ApiModelProperty(value = "话题名称")
    private String topicName;

}
