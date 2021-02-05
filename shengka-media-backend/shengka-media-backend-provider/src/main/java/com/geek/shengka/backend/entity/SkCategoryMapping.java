package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sk_category_mapping
 * @author 
 */
@Data
@ApiModel(value = "频道与内容中心类别映射")
public class SkCategoryMapping implements Serializable {
    private static final long serialVersionUID = 169060662639049576L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 频道id
     */
    @ApiModelProperty(value = "频道id")
    private Long categoryId;

    /**
     * 内容中心类别id
     */
    @ApiModelProperty(value = "内容中心类别id")
    private Long contentCategoryId;

    /**
     * 内容中心类别名称
     */
    @ApiModelProperty(value = "内容中心类别名称")
    private String contentCategoryName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}