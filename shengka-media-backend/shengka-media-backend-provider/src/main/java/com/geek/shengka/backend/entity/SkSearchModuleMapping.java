package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qubianzhong
 * @Date 10:37 2019/8/5
 */
@Data
@ApiModel(value = "搜索页与模块匹配表")
public class SkSearchModuleMapping implements Serializable {
    private static final long serialVersionUID = 4770054384082257054L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

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
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

}