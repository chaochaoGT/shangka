package com.geek.shengka.backend.params.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:34
 */
@Data
@ApiModel(value = "渠道")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkChannelListNoPageResParam implements Serializable {


    private static final long serialVersionUID = -6412689878765002647L;
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
}
