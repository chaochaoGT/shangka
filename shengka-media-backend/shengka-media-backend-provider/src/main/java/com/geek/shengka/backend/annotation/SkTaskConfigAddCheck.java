package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkTaskAwardAmountTypeEnum;
import com.geek.shengka.backend.enums.SkTaskTriggerEventEnum;
import com.geek.shengka.backend.enums.SkTaskTypeEnum;
import com.geek.shengka.backend.params.req.SkTaskConfigAddReqParam;
import org.springframework.util.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author qubianzhong
 * @date 2019/8/1 16:45
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SkTaskConfigAddCheck.SkTaskConfigAddCheckValidator.class)
public @interface SkTaskConfigAddCheck {

    String message() default "新增任务配置参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkTaskConfigAddCheckValidator implements ConstraintValidator<SkTaskConfigAddCheck, SkTaskConfigAddReqParam> {

        @Override
        public boolean isValid(SkTaskConfigAddReqParam addReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            if (addReqParam == null) {
                message = "Body参数不能为空";
                valid = false;
            } else if (StringUtils.isEmpty(addReqParam.getTaskName())) {
                message = "任务名称不能为空";
                valid = false;
            } else if (addReqParam.getTriggerEvent() == null) {
                message = "触发事件不能为空";
                valid = false;
            } else if (!SkTaskTriggerEventEnum.isExist(addReqParam.getTriggerEvent())) {
                message = "触发事件参数异常";
                valid = false;
            } else if (addReqParam.getTaskType() == null) {
                message = "任务类型不能为空";
                valid = false;
            } else if (!SkTaskTypeEnum.isExist(addReqParam.getTaskType())) {
                message = "任务类型参数异常";
                valid = false;
            }
            if (addReqParam != null
                    && SkTaskTypeEnum.DAY_TASK.getValue().equals(addReqParam.getTaskType())) {
                if (addReqParam.getReceiveLimit() == null) {
                    message = "领取次数不能为空";
                    valid = false;
                } else if (addReqParam.getReceiveLimit() < 0) {
                    message = "领取次数不能小于0";
                    valid = false;
                } else if (addReqParam.getAwardAmountType() == null) {
                    message = "奖励额度类型不能为空";
                    valid = false;
                } else if (!SkTaskAwardAmountTypeEnum.isExist(addReqParam.getAwardAmountType())) {
                    message = "奖励额度类型参数异常";
                    valid = false;
                }
            }

            if (addReqParam != null
                    && SkTaskAwardAmountTypeEnum.FIXED.getValue().equals(addReqParam.getAwardAmountType())) {
                if (addReqParam.getAwardFixAmount() == null) {
                    message = "固定额度类型，必须设置固定额度";
                    valid = false;
                } else if (addReqParam.getAwardFixAmount() < 0) {
                    message = "固定额度类型，固定额度不能小于0";
                    valid = false;
                }
            }
            if (addReqParam != null
                    && SkTaskAwardAmountTypeEnum.RANDOM.getValue().equals(addReqParam.getAwardAmountType())) {
                if (addReqParam.getAwardMin() == null) {
                    message = "随机额度类型，必须设置额度下限";
                    valid = false;
                } else if (addReqParam.getAwardMax() == null) {
                    message = "随机额度类型，必须设置额度上限";
                    valid = false;
                } else if (addReqParam.getAwardMax() < 0) {
                    message = "随机额度类型，额度上限不能小于0";
                    valid = false;
                } else if (addReqParam.getAwardMin() < 0) {
                    message = "随机额度类型，额度下限不能小于0";
                    valid = false;
                } else if (addReqParam.getAwardMin() > addReqParam.getAwardMax()) {
                    message = "奖励额下限不能大于奖励额上限";
                    valid = false;
                }
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
