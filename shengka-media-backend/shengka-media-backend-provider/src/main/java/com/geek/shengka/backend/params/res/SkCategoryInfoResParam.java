package com.geek.shengka.backend.params.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geek.shengka.backend.entity.SkCategory;
import com.geek.shengka.backend.enums.SkCategoryEnableEnum;
import com.geek.shengka.backend.enums.SkCategoryTypeEnum;
import com.geek.shengka.backend.params.req.SkCategoryMappingAddReqParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:34
 */
@Data
@ApiModel(value = "频道")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkCategoryInfoResParam extends SkCategory implements Serializable {

    private static final long serialVersionUID = 4072462679166970060L;

    /**
     * 映射的内容分类
     */
    @ApiModelProperty(value = "映射的内容分类")
    private List<SkCategoryMappingAddReqParam> categoryMappings;


    /**
     * 频道类型
     */
    @ApiModelProperty(value = "频道类型")
    private String categoryTypeName;

    /**
     * 禁用/启用
     */
    @ApiModelProperty(value = "禁用/启用")
    private String enableName;

    public String getCategoryTypeName() {
        return SkCategoryTypeEnum.getDesc(super.getCategoryType());
    }

    public String getEnableName() {
        return SkCategoryEnableEnum.getDesc(super.getEnable());
    }
}
