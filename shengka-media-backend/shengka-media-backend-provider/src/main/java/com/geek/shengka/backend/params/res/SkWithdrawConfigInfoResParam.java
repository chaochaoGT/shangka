package com.geek.shengka.backend.params.res;

import com.geek.shengka.backend.entity.SkWithdrawConfig;
import com.geek.shengka.backend.enums.SkWithdrawConfigEnableEnum;
import com.geek.shengka.backend.enums.SkWithdrawConfigTypeEnum;
import com.geek.shengka.backend.enums.SkWithdrawConfigUnlockEventEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:32
 */
@Data
public class SkWithdrawConfigInfoResParam extends SkWithdrawConfig implements Serializable {
    private static final long serialVersionUID = 6828160771631935690L;

    /**
     * 提现类型
     */
    @ApiModelProperty(value = "提现类型")
    private String withdrawTypeName;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String enableName;

    /**
     * 活动提现解锁事件
     */
    @ApiModelProperty(value = "活动提现解锁事件")
    private String unlockEventName;

    public String getWithdrawTypeName() {
        return SkWithdrawConfigTypeEnum.getDesc(super.getWithdrawType());
    }

    public String getEnableName() {
        return SkWithdrawConfigEnableEnum.getDesc(super.getEnable());
    }

    public String getUnlockEventName() {
        return SkWithdrawConfigUnlockEventEnum.getDesc(super.getUnlockEvent());
    }
}
