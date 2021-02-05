package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_channel_category
 *
 * @author
 */
@Data
@ApiModel(value = "渠道和频道关系")
public class SkChannelCategory implements Serializable {
    private static final long serialVersionUID = 7881574152295094174L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 渠道id
     */
    @ApiModelProperty(value = "渠道id")
    private Long channelId;

    /**
     * 频道id
     */
    @ApiModelProperty(value = "频道id")
    private Long categoryId;

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