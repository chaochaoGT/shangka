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
@ApiModel(description = "榜单视频")
public class SkRankMappingListReqParam extends BasePageReqParam implements Serializable {

    private static final long serialVersionUID = -490435899436998827L;
    @ApiModelProperty(value = "榜单ID")
    private Long rankId;

    @ApiModelProperty(value = "榜单类型（1-视频  2-用户  3-话题）", allowableValues = "1,2,3")
    private Byte rankType;

}
