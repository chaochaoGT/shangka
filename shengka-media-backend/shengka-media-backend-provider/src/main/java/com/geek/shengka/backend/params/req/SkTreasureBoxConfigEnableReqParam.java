package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:41
 */
@Data
@ApiModel(description = "宝箱开关修改")
public class SkTreasureBoxConfigEnableReqParam implements Serializable {
    private static final long serialVersionUID = 5303796331647390336L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 0-启用,1-禁用
     */
    @ApiModelProperty(value = "0-启用,1-禁用", allowableValues = "0,1")
    private Byte enable;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
