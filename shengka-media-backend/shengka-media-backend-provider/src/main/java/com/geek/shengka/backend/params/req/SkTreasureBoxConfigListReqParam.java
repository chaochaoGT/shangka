package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:32
 */
@Data
public class SkTreasureBoxConfigListReqParam extends BasePageReqParam implements Serializable {

    private static final long serialVersionUID = 2956949792389514395L;

    /**
     * 宝箱活动名称
     */
    @ApiModelProperty(value = "宝箱活动名称")
    private String boxName;

    /**
     * 0-启用,1-禁用
     */
    @ApiModelProperty(value = " 0-启用,1-禁用", allowableValues = "0,1")
    private Byte enable;
}
