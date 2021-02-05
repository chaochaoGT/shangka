package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkWithdrawConfigTypeEnum;
import com.geek.shengka.backend.enums.SkWithdrawConfigUnlockEventEnum;
import com.geek.shengka.backend.params.req.SkWithdrawConfigUpdateReqParam;

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
@Constraint(validatedBy = SkWithdrawConfigUpdateCheck.SkWithdrawConfigUpdateCheckValidator.class)
public @interface SkWithdrawConfigUpdateCheck {

    String message() default "新增任务配置参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkWithdrawConfigUpdateCheckValidator implements ConstraintValidator<SkWithdrawConfigUpdateCheck, SkWithdrawConfigUpdateReqParam> {

        @Override
        public boolean isValid(SkWithdrawConfigUpdateReqParam updateReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            if (updateReqParam == null) {
                message = "Body参数不能为空";
                valid = false;
            } else if (updateReqParam.getId() == null) {
                message = "ID不能为空";
                valid = false;
            } else if (updateReqParam.getWithdrawAmount() != null
                    && updateReqParam.getWithdrawAmount().doubleValue() < 1) {
                message = "提现金额不能小于1.";
                valid = false;
            } else if (updateReqParam.getWithdrawType() != null
                    && !SkWithdrawConfigTypeEnum.isExist(updateReqParam.getWithdrawType())) {
                message = "提现类型参数异常";
                valid = false;
            }
            if (updateReqParam != null
                    && SkWithdrawConfigTypeEnum.ACTIVITY.getValue().equals(updateReqParam.getWithdrawType())) {
                if (updateReqParam.getUnlockEvent() == null) {
                    message = "活动提现,提现解锁事件不能为空";
                    valid = false;
                } else if (!SkWithdrawConfigUnlockEventEnum.isExist(updateReqParam.getUnlockEvent())) {
                    message = "活动提现,提现解锁事件参数异常";
                    valid = false;
                }
                if (SkWithdrawConfigUnlockEventEnum.CONSECUTIVE_LOGIN_DAYS.getValue().equals(updateReqParam.getUnlockEvent())) {
                    if (updateReqParam.getUnlockLoginDay() == null) {
                        message = "连续登录天数提现解锁事件,连续登录天数不能为空";
                        valid = false;
                    } else if (updateReqParam.getUnlockLoginDay() < 0) {
                        message = "连续登录天数提现解锁事件,连续登录天数不能小于0";
                        valid = false;
                    }
                }
                if (SkWithdrawConfigUnlockEventEnum.CUMULATIVE_VIEWING_TIME.getValue().equals(updateReqParam.getUnlockEvent())) {
                    if (updateReqParam.getUnlockWatchTime() == null) {
                        message = "累计观看时长提现解锁事件,累计观看时长不能为空";
                        valid = false;
                    } else if (updateReqParam.getUnlockWatchTime() < 0) {
                        message = "累计观看时长提现解锁事件,累计观看时长不能小于0";
                        valid = false;
                    }
                }
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
