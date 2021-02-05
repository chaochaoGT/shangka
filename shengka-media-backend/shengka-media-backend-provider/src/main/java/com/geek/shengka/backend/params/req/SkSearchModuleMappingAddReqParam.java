package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/5 10:33
 */
@Data
@ApiModel(description = "搜索页模块匹配新增")
public class SkSearchModuleMappingAddReqParam implements Serializable {
    private static final long serialVersionUID = -2927945518229504742L;
    /**
     * 配置表id
     */
    @ApiModelProperty(value = "配置表id")
    private Long configId;

    /**
     * 类型（1-视频  2-话题）
     */
    @ApiModelProperty(value = "类型（1-视频  2-话题）", allowableValues = "1,2")
    private Byte moduleType;

    /**
     * 映射资源id
     */
    @ApiModelProperty(value = "映射资源id")
    private String sourceId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer seq;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
}
