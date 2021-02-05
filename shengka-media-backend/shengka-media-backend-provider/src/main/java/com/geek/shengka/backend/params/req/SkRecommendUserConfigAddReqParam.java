package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/22 13:47
 */
@Data
@ApiModel
public class SkRecommendUserConfigAddReqParam implements Serializable {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 排名
     */
    @ApiModelProperty(value = "排名")
    private Integer seq;

    /**
     * 展示数量
     */
    @ApiModelProperty(value = "展示数量")
    private Integer showNum;

    /**
     * 用户icon
     */
    @ApiModelProperty(value = "用户icon")
    private String userIcon;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;
}
