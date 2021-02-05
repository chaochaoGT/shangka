package com.geek.shengka.backend.params.res;

import com.geek.shengka.backend.entity.SkSearchPageConfig;
import com.geek.shengka.backend.enums.SkSearchPageConfigCodeEnum;
import com.geek.shengka.backend.enums.SkSearchPageConfigEnableEnum;
import com.geek.shengka.backend.enums.SkSearchPageConfigMoudleTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/5 10:28
 */
@Data
@ApiModel("搜索页列表")
public class SkSearchPageConfigListResParam extends SkSearchPageConfig implements Serializable {
    private static final long serialVersionUID = -5463168200170268911L;

    /**
     * 模块编号
     */
    @ApiModelProperty(value = "模块编号")
    private String moduleCodeName;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String moduleTypeName;

    /**
     * 有效-无效
     */
    @ApiModelProperty(value = "有效-无效")
    private String enableName;

    public String getModuleCodeName() {
        return SkSearchPageConfigCodeEnum.getDesc(super.getModuleCode());
    }

    public String getModuleTypeName() {
        return SkSearchPageConfigMoudleTypeEnum.getDesc(super.getModuleType());
    }

    public String getEnableName() {
        return SkSearchPageConfigEnableEnum.getDesc(super.getEnable());
    }
}
