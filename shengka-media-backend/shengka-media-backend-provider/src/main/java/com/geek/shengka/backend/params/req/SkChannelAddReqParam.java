package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:53
 */
@Data
@ApiModel(description = "渠道新增")
public class SkChannelAddReqParam implements Serializable {
    private static final long serialVersionUID = -657719206205493929L;

    /**
     * 渠道名称
     */
    @ApiModelProperty(value = "渠道名称")
    private String channelName;

    /**
     * 渠道编号
     */
    @ApiModelProperty(value = "渠道编号")
    private String channelCode;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

}
