package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_category
 *
 * @author
 */
@Data
@ApiModel(value = "频道")
public class SkCategory implements Serializable {
    private static final long serialVersionUID = 4634568533858915219L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 频道名称
     */
    @ApiModelProperty(value = "频道名称")
    private String categoryName;

    /**
     * 频道类型（1-默认  2-可选）
     */
    @ApiModelProperty(value = "频道类型（1-默认  2-可选）", allowableValues = "1,2")
    private Byte categoryType;

    /**
     * 0-禁用,1-启用
     */
    @ApiModelProperty(value = "0-禁用,1-启用")
    private Byte enable;

    /**
     * icon图片
     */
    @ApiModelProperty(value = "icon图片")
    private String iconUrl;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer seq;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

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
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

}