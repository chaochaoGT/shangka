package com.geek.shengka.backend.params.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/8 16:53
 */
@Data
@ApiModel(value = "用户中心的视频分类数据")
public class SkContentCenterCategorysResParam implements Serializable {
    private static final long serialVersionUID = -5149620845907685433L;
    /**
     * 内容中心类别id
     */
    @ApiModelProperty(value = "内容中心类别id")
    private Long id;

    /**
     * 内容中心类别名称
     */
    @ApiModelProperty(value = "内容中心类别名称")
    private String categoryName;

    /**
     * 内容中心类别编号
     */
    @ApiModelProperty(value = "内容中心类别编号")
    private String categoryCode;

    /**
     * 内容中心类别状态
     */
    @ApiModelProperty(value = "内容中心类别状态")
    private String categoryStatus;

    /**
     * 内容中心类别对应视频数量
     */
    @ApiModelProperty(value = "内容中心类别对应视频数量")
    private Integer videoNum;
    /**
     * 内容中心类别是否删除
     */
    @ApiModelProperty(value = "内容中心类别是否删除")
    private Integer isDelete;


    /**
     * 兼容老版本-返回分类编号
     */
    public String getContentCategoryId() {
        return getCategoryCode();
    }

    /**
     * 兼容老版本-返回分类名称
     */
    public String getContentCategoryName() {
        return getCategoryName();
    }
}
