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
@ApiModel(description = "渠道更新")
public class SkChannelUpdateReqParam implements Serializable {
    private static final long serialVersionUID = 3368229587378446115L;
    /**
     * 渠道id
     */
    @ApiModelProperty(value = "渠道id")
    private Long id;

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
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

}
