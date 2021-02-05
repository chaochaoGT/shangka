package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkTaskAwardAmountTypeEnum;
import com.geek.shengka.backend.enums.SkTaskTriggerEventEnum;
import com.geek.shengka.backend.enums.SkTaskTypeEnum;
import com.geek.shengka.backend.params.req.SkTaskConfigUpdateReqParam;

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
@Constraint(validatedBy = SkTaskConfigUpdateCheck.SkTaskConfigUpdateCheckValidator.class)
public @interface SkTaskConfigUpdateCheck {

    String message() default "新增任务配置参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkTaskConfigUpdateCheckValidator implements ConstraintValidator<SkTaskConfigUpdateCheck, SkTaskConfigUpdateReqParam> {

        @Override
        public boolean isValid(SkTaskConfigUpdateReqParam updateReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            if (updateReqParam == null) {
                message = "Body参数不能为空!";
                valid = false;
            } else if (updateReqParam.getId() == null) {
                message = "ID不能为空!";
                valid = false;
            } else if (updateReqParam.getTriggerEvent() != null
                    && !SkTaskTriggerEventEnum.isExist(updateReqParam.getTriggerEvent())) {
                message = "触发事件参数异常!";
                valid = false;
            } else if (updateReqParam.getReceiveLimit() != null
                    && updateReqParam.getReceiveLimit() < 0) {
                message = "领取次数不能小于0";
                valid = false;
            } else if (updateReqParam.getAwardAmountType() != null
                    && !SkTaskAwardAmountTypeEnum.isExist(updateReqParam.getAwardAmountType())) {
                message = "奖励额度类型参数异常!";
                valid = false;
            } else if (updateReqParam.getTaskType() != null
                    && !SkTaskTypeEnum.isExist(updateReqParam.getTaskType())) {
                message = "任务类型参数异常!";
                valid = false;
            }
            if (updateReqParam != null
                    && SkTaskAwardAmountTypeEnum.FIXED.getValue().equals(updateReqParam.getAwardAmountType())) {
                if (updateReqParam.getAwardFixAmount() == null) {
                    message = "固定额度类型，必须设置固定额度!";
                    valid = false;
                } else if (updateReqParam.getAwardFixAmount() < 0) {
                    message = "固定额度类型，固定额度不能小于0!";
                    valid = false;
                }
            }
            if (updateReqParam != null
                    && SkTaskAwardAmountTypeEnum.RANDOM.getValue().equals(updateReqParam.getAwardAmountType())) {
                if (updateReqParam.getAwardMin() == null) {
                    message = "随机额度类型，必须设置额度下限!";
                    valid = false;
                } else if (updateReqParam.getAwardMax() == null) {
                    message = "随机额度类型，必须设置额度上限!";
                    valid = false;
                } else if (updateReqParam.getAwardMax() < 0) {
                    message = "随机额度类型，额度上限不能小于0!";
                    valid = false;
                } else if (updateReqParam.getAwardMin() < 0) {
                    message = "随机额度类型，额度下限不能小于0!";
                    valid = false;
                } else if (updateReqParam.getAwardMin() > updateReqParam.getAwardMax()) {
                    message = "奖励额下限不能大于奖励额上限!";
                    valid = false;
                }
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
