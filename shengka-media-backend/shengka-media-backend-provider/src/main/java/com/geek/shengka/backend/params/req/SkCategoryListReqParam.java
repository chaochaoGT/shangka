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
@ApiModel(description = "频道")
public class SkCategoryListReqParam extends BasePageReqParam implements Serializable {
    private static final long serialVersionUID = -2425982571213241698L;

    @ApiModelProperty(value = "频道名称")
    private String categoryName;

    @ApiModelProperty(value = "0-禁用,1-启用")
    private Integer enable;
}
