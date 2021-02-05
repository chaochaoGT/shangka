package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:53
 */
@Data
@ApiModel(description = "频道新增")
public class SkCategoryAddReqParam implements Serializable {
    private static final long serialVersionUID = 3368229587378446115L;
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
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
    * 映射的内容分类
    */
    @ApiModelProperty(value = "映射的内容分类")
    private List<SkCategoryMappingAddReqParam> categoryMappings;

}
