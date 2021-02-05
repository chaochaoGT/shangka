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
@ApiModel(description = "版本")
public class SkVersionListReqParam extends BasePageReqParam implements Serializable {

    private static final long serialVersionUID = 2168763306249590053L;
    @ApiModelProperty(value = "渠道名称")
    private String channelName;

}
