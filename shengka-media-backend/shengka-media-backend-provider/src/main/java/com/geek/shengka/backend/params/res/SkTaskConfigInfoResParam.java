package com.geek.shengka.backend.params.res;

import com.geek.shengka.backend.entity.SkTaskConfig;
import com.geek.shengka.backend.enums.SkTaskAwardAmountTypeEnum;
import com.geek.shengka.backend.enums.SkTaskStateEnum;
import com.geek.shengka.backend.enums.SkTaskTriggerEventEnum;
import com.geek.shengka.backend.enums.SkTaskTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:32
 */
@Data
public class SkTaskConfigInfoResParam extends SkTaskConfig implements Serializable {
    private static final long serialVersionUID = 6413004642279704891L;

    /**
     * 任务类型
     */
    @ApiModelProperty(value = "任务类型")
    private String taskTypeName;

    /**
     * 触发事件
     */
    @ApiModelProperty(value = "触发事件")
    private String triggerEventName;

    /**
     * 奖励额度类型
     */
    @ApiModelProperty(value = "奖励额度类型")
    private String awardAmountTypeName;

    /**
     * 任务状态
     */
    @ApiModelProperty(value = "任务状态")
    private String taskStateName;

    public String getTaskTypeName() {
        return SkTaskTypeEnum.getDesc(super.getTaskType());
    }

    public String getTriggerEventName() {
        return SkTaskTriggerEventEnum.getDesc(super.getTriggerEvent());
    }

    public String getAwardAmountTypeName() {
        return SkTaskAwardAmountTypeEnum.getDesc(super.getAwardAmountType());
    }

    public String getTaskStateName() {
        return SkTaskStateEnum.getDesc(super.getTaskState());
    }
}
