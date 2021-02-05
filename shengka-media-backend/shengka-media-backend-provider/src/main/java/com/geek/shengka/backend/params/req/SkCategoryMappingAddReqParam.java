package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * sk_category_mapping
 *
 * @author
 */
@Data
@ApiModel(description = "频道和内容分类映射")
public class SkCategoryMappingAddReqParam implements Serializable {
    private static final long serialVersionUID = 169060662639049576L;

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

}