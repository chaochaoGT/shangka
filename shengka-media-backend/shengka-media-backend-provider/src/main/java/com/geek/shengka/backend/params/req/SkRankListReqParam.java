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
@ApiModel(description = "榜单")
public class SkRankListReqParam extends BasePageReqParam implements Serializable {

    private static final long serialVersionUID = 2637249295097432831L;

    @ApiModelProperty(value = "榜单名称")
    private String rankName;

}
