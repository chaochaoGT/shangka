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
@ApiModel(description = "搜索页配置映射查询")
public class SkSearchPageConfigMappingListReqParam extends BasePageReqParam implements Serializable {

    private static final long serialVersionUID = -5959910315368982810L;
    @ApiModelProperty(value = "配置ID")
    private Long configId;

    @ApiModelProperty(value = "类型（1-视频  2-话题）", allowableValues = "1,2")
    private Byte moduleType;

}
