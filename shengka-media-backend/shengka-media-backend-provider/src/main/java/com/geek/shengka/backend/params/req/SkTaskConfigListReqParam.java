package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/2 17:32
 */
@Data
@Validated
@ApiModel(description = "任务配置列表")
public class SkTaskConfigListReqParam extends BasePageReqParam implements Serializable {
    private static final long serialVersionUID = 8725014686081019381L;

    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    private String taskName;

    /**
     * 任务类型（1-每日任务  2-新手任务）
     */
    @ApiModelProperty(value = "任务类型（1-每日任务  2-新手任务）", allowableValues = "1,2")
    @NotNull(message = "任务类型不能为空")
    private Byte taskType;
}
