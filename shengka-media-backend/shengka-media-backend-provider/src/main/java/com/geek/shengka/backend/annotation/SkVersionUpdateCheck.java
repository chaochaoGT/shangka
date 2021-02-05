package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkVersionAppTypeEnum;
import com.geek.shengka.backend.enums.SkVersionForcedUpdateEnum;
import com.geek.shengka.backend.enums.SkVersionPopupEnum;
import com.geek.shengka.backend.enums.SkVersionStateEnum;
import com.geek.shengka.backend.params.req.SkVersionUpdateReqParam;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author qubianzhong
 * @date 2019/8/1 10:45
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SkVersionUpdateCheck.SkVersionUpdateCheckValidator.class)
public @interface SkVersionUpdateCheck {

    String message() default "更新版本参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkVersionUpdateCheckValidator implements ConstraintValidator<SkVersionUpdateCheck, SkVersionUpdateReqParam> {

        @Override
        public boolean isValid(SkVersionUpdateReqParam updateReqParam, ConstraintValidatorContext context) {
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
            } else if (updateReqParam.getState() != null
                    && !SkVersionStateEnum.isExist(updateReqParam.getState())) {
                message = "版本状态参数异常，只允许为：有效、无效、已删除";
                valid = false;
            } else if (updateReqParam.getAppType() != null
                    && !SkVersionAppTypeEnum.isExist(updateReqParam.getAppType())) {
                message = "APP类型参数异常，只允许为：iPhone、iPad、Android、微信、H5";
                valid = false;
            } else if (updateReqParam.getForcedUpdate() != null
                    && !SkVersionForcedUpdateEnum.isExist(updateReqParam.getForcedUpdate())) {
                message = "更新类型参数异常，只允许为：常规更新、强制更新";
                valid = false;
            } else if (updateReqParam.getPopup() != null
                    && !SkVersionPopupEnum.isExist(updateReqParam.getPopup())) {
                message = "弹窗类型参数异常，只允许为：弹窗、不弹窗";
                valid = false;
            } else if (updateReqParam.getBeginTime() != null
                    && updateReqParam.getEndTime() != null
                    && updateReqParam.getEndTime().before(updateReqParam.getBeginTime())) {
                message = "更新结束时间不能小于更新开始时间!";
                valid = false;
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
