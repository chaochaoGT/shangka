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
@ApiModel(description = "榜单开关")
public class SkRankEnableReqParam implements Serializable {
    private static final long serialVersionUID = -5268288529224967659L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;


    /**
     * 0-启用,1-禁用
     */
    @ApiModelProperty(value = " 0-启用,1-禁用", allowableValues = "0,1")
    private Integer enable;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

}
