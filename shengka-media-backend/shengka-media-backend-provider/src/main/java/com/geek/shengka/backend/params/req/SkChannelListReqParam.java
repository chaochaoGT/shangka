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
@ApiModel(description = "渠道")
public class SkChannelListReqParam extends BasePageReqParam implements Serializable {

    private static final long serialVersionUID = 5656337683638262706L;
    @ApiModelProperty(value = "渠道名称")
    private String channelName;

    /**
     * 渠道编号
     */
    @ApiModelProperty(value = "渠道编号")
    private String channelCode;

}
