package com.geek.shengka.backend.params.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geek.shengka.backend.entity.SkVersion;
import com.geek.shengka.backend.enums.SkVersionAppTypeEnum;
import com.geek.shengka.backend.enums.SkVersionForcedUpdateEnum;
import com.geek.shengka.backend.enums.SkVersionPopupEnum;
import com.geek.shengka.backend.enums.SkVersionStateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/7/31 17:34
 */
@Data
@ApiModel(value = "版本")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkVersionInfoResParam extends SkVersion implements Serializable {

    private static final long serialVersionUID = -4180764681791880288L;

    /**
     * APP类型名称
     */
    @ApiModelProperty(value = "APP类型名称")
    private String appTypeName;

    /**
     * 是否强制更新
     */
    @ApiModelProperty(value = "是否强制更新")
    private String forcedUpdateName;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String stateName;

    /**
     * 是否弹窗
     */
    @ApiModelProperty(value = "是否弹窗")
    private String popupName;

    public String getAppTypeName() {
        return SkVersionAppTypeEnum.getDesc(super.getAppType());
    }

    public String getForcedUpdateName() {
        return SkVersionForcedUpdateEnum.getDesc(super.getForcedUpdate());
    }

    public String getStateName() {
        return SkVersionStateEnum.getDesc(super.getState());
    }

    public String getPopupName() {
        return SkVersionPopupEnum.getDesc(super.getPopup());
    }
}
