package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:32
 */
@Data
public class SkNativeMessageListReqParam extends BasePageReqParam implements Serializable {
    private static final long serialVersionUID = 8725014686081019381L;

    /**
     * 消息标题
     */
    @ApiModelProperty(value = "消息标题")
    private String messageTitle;

}
