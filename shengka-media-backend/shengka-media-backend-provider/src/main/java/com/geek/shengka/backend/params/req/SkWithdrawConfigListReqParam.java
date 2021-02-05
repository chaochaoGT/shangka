package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:32
 */
@Data
public class SkWithdrawConfigListReqParam extends BasePageReqParam implements Serializable {
    private static final long serialVersionUID = 8725014686081019381L;

    /**
     * 提现类型（1-常规提现  2-活动提现）
     */
    @ApiModelProperty(value = "提现类型（1-常规提现  2-活动提现）", allowableValues = "1,2")
    private Byte withdrawType;

    /**
     * 状态（0-启用  1-禁用）
     */
    @ApiModelProperty(value = "状态（0-启用  1-禁用）", allowableValues = "0,1")
    private Byte enable;

}
