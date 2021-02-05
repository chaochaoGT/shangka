package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:36
 */
@Data
@ApiModel(description = "更新站内信")
public class SkNativeMessageUpdateReqParam implements Serializable {

    private static final long serialVersionUID = -4310507624063003386L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 消息标题
     */
    @ApiModelProperty(value = "消息标题")
    private String messageTitle;

    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String messageContent;

    /**
     * 0-有效  1-无效
     */
    @ApiModelProperty(value = "0-有效  1-无效", allowableValues = "0,1")
    private Byte enable;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
