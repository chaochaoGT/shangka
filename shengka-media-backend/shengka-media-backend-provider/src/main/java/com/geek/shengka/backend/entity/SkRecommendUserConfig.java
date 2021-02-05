package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qubianzhong
 * @Date 13:39 2019/8/22
 */
@Data
@ApiModel
public class SkRecommendUserConfig implements Serializable {
    private static final long serialVersionUID = 5831375859526775925L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

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
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

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