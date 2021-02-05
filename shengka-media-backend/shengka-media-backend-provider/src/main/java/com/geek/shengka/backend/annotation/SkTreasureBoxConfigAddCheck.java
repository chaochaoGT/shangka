package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkTreasureBoxConfigEnableEnum;
import com.geek.shengka.backend.enums.SkTreasureBoxConfigLimitTypeEnum;
import com.geek.shengka.backend.params.req.SkTreasureBoxConfigAddReqParam;

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
@Constraint(validatedBy = SkTreasureBoxConfigAddCheck.SkTreasureBoxConfigAddCheckValidator.class)
public @interface SkTreasureBoxConfigAddCheck {

    String message() default "新增任务配置参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkTreasureBoxConfigAddCheckValidator implements ConstraintValidator<SkTreasureBoxConfigAddCheck, SkTreasureBoxConfigAddReqParam> {

        @Override
        public boolean isValid(SkTreasureBoxConfigAddReqParam addReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            if (addReqParam == null) {
                message = "Body参数不能为空";
                valid = false;
            } else if (addReqParam.getStartTime() == null) {
                message = "开始时间不能为空";
                valid = false;
            } else if (addReqParam.getEndTime() == null) {
                message = "结束时间不能为空";
                valid = false;
            } else if (addReqParam.getEndTime().before(addReqParam.getStartTime())) {
                message = "结束时间不能小于开始时间";
                valid = false;
            } else if (addReqParam.getCoinMin() == null) {
                message = "宝箱金币下限不能为空";
                valid = false;
            } else if (addReqParam.getCoinMin() < 0) {
                message = "宝箱金币下限不能小于0";
                valid = false;
            } else if (addReqParam.getCoinMax() == null) {
                message = "宝箱金币上限不能为空";
                valid = false;
            } else if (addReqParam.getCoinMax() < 0) {
                message = "宝箱金币上限不能小于0";
                valid = false;
            } else if (addReqParam.getCoinMin() > addReqParam.getCoinMax()) {
                message = "宝箱金币上限不能小于宝箱金币下限";
                valid = false;
            } else if (addReqParam.getEnable() != null
                    && SkTreasureBoxConfigEnableEnum.isExist(addReqParam.getEnable())) {
                message = "宝箱是否有效配置参数异常，只能为启用或禁用";
                valid = false;
            } else if (addReqParam.getLimitType() == null) {
                message = "宝箱限制类型不能为空";
                valid = false;
            } else if (!SkTreasureBoxConfigLimitTypeEnum.isExist(addReqParam.getLimitType())) {
                message = "宝箱限制类型参数异常";
                valid = false;
            } else if (addReqParam.getWatchDuration() == null) {
                message = "累计时长不能为空";
                valid = false;
            } else if (addReqParam.getWatchDuration() < 0) {
                message = "累计时长不能小于0";
                valid = false;
            }
            if (addReqParam != null
                    && SkTreasureBoxConfigLimitTypeEnum.NUMBER.getValue().equals(addReqParam.getLimitType())) {
                if (addReqParam.getLimitCount() == null) {
                    message = "次数限制,每日领取次数上限不能为空";
                    valid = false;
                } else if (addReqParam.getLimitCount() < 0) {
                    message = "次数限制,每日领取次数上限不能小于0";
                    valid = false;
                }
            }
            if (addReqParam != null
                    && SkTreasureBoxConfigLimitTypeEnum.GOLD.getValue().equals(addReqParam.getLimitType())) {
                if (addReqParam.getLimitCoinAmount() == null) {
                    message = "金币数限制,每日领取金币上限不能为空";
                    valid = false;
                } else if (addReqParam.getLimitCoinAmount() < 0) {
                    message = "金币数限制,每日领取金币上限不能小于0";
                    valid = false;
                }
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
