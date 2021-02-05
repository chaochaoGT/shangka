package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkTreasureBoxConfigEnableEnum;
import com.geek.shengka.backend.enums.SkTreasureBoxConfigLimitTypeEnum;
import com.geek.shengka.backend.params.req.SkTreasureBoxConfigUpdateReqParam;

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
@Constraint(validatedBy = SkTreasureBoxConfigUpdateCheck.SkTreasureBoxConfigUpdateCheckValidator.class)
public @interface SkTreasureBoxConfigUpdateCheck {

    String message() default "新增任务配置参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkTreasureBoxConfigUpdateCheckValidator implements ConstraintValidator<SkTreasureBoxConfigUpdateCheck, SkTreasureBoxConfigUpdateReqParam> {

        @Override
        public boolean isValid(SkTreasureBoxConfigUpdateReqParam updateReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            boolean oneDate = (updateReqParam.getStartTime() != null && updateReqParam.getEndTime() == null)
                    || (updateReqParam.getStartTime() == null && updateReqParam.getEndTime() != null);
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            if (updateReqParam == null) {
                message = "Body参数不能为空！";
                valid = false;
            } else if (updateReqParam.getId() == null) {
                message = "ID不能为空！";
                valid = false;
            } else if (
                    updateReqParam.getStartTime() != null
                            && updateReqParam.getEndTime() != null
                            && updateReqParam.getEndTime().before(updateReqParam.getStartTime())) {
                message = "结束时间不能小于开始时间！";
                valid = false;
            } else if (oneDate) {
                message = "结束时间和开始时间不能只更新其中一个！";
                valid = false;
            } else if (updateReqParam.getCoinMin() != null
                    && updateReqParam.getCoinMin() < 0) {
                message = "宝箱金币下限不能小于0！";
                valid = false;
            } else if (updateReqParam.getCoinMax() != null
                    && updateReqParam.getCoinMax() < 0) {
                message = "宝箱金币上限不能小于0！";
                valid = false;
            } else if (updateReqParam.getCoinMin() != null
                    && updateReqParam.getCoinMax() != null
                    && updateReqParam.getCoinMin() > updateReqParam.getCoinMax()) {
                message = "宝箱金币上限不能小于宝箱金币下限！";
                valid = false;
            } else if (updateReqParam.getEnable() != null
                    && SkTreasureBoxConfigEnableEnum.isExist(updateReqParam.getEnable())) {
                message = "宝箱是否有效配置参数异常，只能为启用或禁用！";
                valid = false;
            } else if (updateReqParam.getLimitType() != null
                    && !SkTreasureBoxConfigLimitTypeEnum.isExist(updateReqParam.getLimitType())) {
                message = "宝箱限制类型参数异常！";
                valid = false;
            } else if (updateReqParam.getWatchDuration() != null
                    && updateReqParam.getWatchDuration() < 0) {
                message = "累计时长不能小于0";
                valid = false;
            }
            if (updateReqParam != null
                    && SkTreasureBoxConfigLimitTypeEnum.NUMBER.getValue().equals(updateReqParam.getLimitType())) {
                if (updateReqParam.getLimitCount() == null) {
                    message = "次数限制,每日领取次数上限不能为空！";
                    valid = false;
                } else if (updateReqParam.getLimitCount() < 0) {
                    message = "次数限制,每日领取次数上限不能小于0！";
                    valid = false;
                }
            }
            if (updateReqParam != null
                    && SkTreasureBoxConfigLimitTypeEnum.GOLD.getValue().equals(updateReqParam.getLimitType())) {
                if (updateReqParam.getLimitCoinAmount() == null) {
                    message = "金币数限制,每日领取金币上限不能为空！";
                    valid = false;
                } else if (updateReqParam.getLimitCoinAmount() < 0) {
                    message = "金币数限制,每日领取金币上限不能小于0！";
                    valid = false;
                }
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
