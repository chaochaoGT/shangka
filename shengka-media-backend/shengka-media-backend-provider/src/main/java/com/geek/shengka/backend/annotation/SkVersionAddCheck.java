package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkVersionAppTypeEnum;
import com.geek.shengka.backend.enums.SkVersionForcedUpdateEnum;
import com.geek.shengka.backend.enums.SkVersionPopupEnum;
import com.geek.shengka.backend.enums.SkVersionStateEnum;
import com.geek.shengka.backend.params.req.SkVersionAddReqParam;

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
@Constraint(validatedBy = SkVersionAddCheck.SkVersionAddCheckValidator.class)
public @interface SkVersionAddCheck {

    String message() default "新增版本参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkVersionAddCheckValidator implements ConstraintValidator<SkVersionAddCheck, SkVersionAddReqParam> {

        @Override
        public boolean isValid(SkVersionAddReqParam addReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            if (addReqParam == null) {
                message = "Body参数不能为空!";
                valid = false;
            } else if (addReqParam.getState() != null
                    && !SkVersionStateEnum.isExist(addReqParam.getState())) {
                message = "版本状态参数异常，只允许为：有效、无效、已删除!";
                valid = false;
            } else if (addReqParam.getAppType() != null
                    && !SkVersionAppTypeEnum.isExist(addReqParam.getAppType())) {
                message = "APP类型参数异常，只允许为：iPhone、iPad、Android、微信、H5!";
                valid = false;
            } else if (addReqParam.getForcedUpdate() != null
                    && !SkVersionForcedUpdateEnum.isExist(addReqParam.getForcedUpdate())) {
                message = "更新类型参数异常，只允许为：常规更新、强制更新!";
                valid = false;
            } else if (addReqParam.getPopup() != null
                    && !SkVersionPopupEnum.isExist(addReqParam.getPopup())) {
                message = "弹窗类型参数异常，只允许为：弹窗、不弹窗!";
                valid = false;
            } else if (addReqParam.getBeginTime() == null) {
                message = "更新开始时间不能为空!";
                valid = false;
            } else if (addReqParam.getEndTime() == null) {
                message = "更新结束时间不能为空!";
                valid = false;
            } else if (addReqParam.getEndTime().before(addReqParam.getBeginTime())) {
                message = "更新结束时间不能小于更新开始时间!";
                valid = false;
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
