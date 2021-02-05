package com.geek.shengka.backend.params.res;

import com.geek.shengka.backend.entity.SkTreasureBoxConfig;
import com.geek.shengka.backend.enums.SkTreasureBoxConfigEnableEnum;
import com.geek.shengka.backend.enums.SkTreasureBoxConfigLimitTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:32
 */
@Data
@ApiModel(value = "宝箱列表")
public class SkTreasureBoxConfigListResParam extends SkTreasureBoxConfig implements Serializable {
    private static final long serialVersionUID = 6413004642279704891L;

    /**
     * 限制类型
     */
    @ApiModelProperty(value = "限制类型")
    private String limitTypeName;

    /**
     * 启用,禁用
     */
    @ApiModelProperty(value = "启用,禁用")
    private String enableName;

    public String getLimitTypeName() {
        return SkTreasureBoxConfigLimitTypeEnum.getDesc(super.getLimitType());
    }

    public String getEnableName() {
        return SkTreasureBoxConfigEnableEnum.getDesc(super.getEnable());
    }
}
