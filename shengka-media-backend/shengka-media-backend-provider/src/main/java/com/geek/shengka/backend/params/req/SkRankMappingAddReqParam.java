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
@ApiModel(description = "榜单视频映射新增")
public class SkRankMappingAddReqParam implements Serializable {
    private static final long serialVersionUID = 7500080142894670626L;
    /**
     * 榜单id
     */
    @ApiModelProperty(value = "榜单id")
    private Long rankId;

    /**
     * 榜单类型（1-视频  2-用户  3-话题）
     */
    @ApiModelProperty(value = "榜单类型（1-视频  2-用户  3-话题）", allowableValues = "1,2,3")
    private Byte rankType;

    /**
     * 关联id（视频id/用户id/话题id）
     */
    @ApiModelProperty(value = "关联id（视频id/用户id/话题id）")
    private String relId;

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
