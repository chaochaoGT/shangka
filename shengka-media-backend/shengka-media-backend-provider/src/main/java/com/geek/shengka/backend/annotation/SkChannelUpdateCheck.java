package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.params.req.SkChannelUpdateReqParam;
import org.springframework.util.StringUtils;

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
@Constraint(validatedBy = SkChannelUpdateCheck.SkChannelUpdateCheckValidator.class)
public @interface SkChannelUpdateCheck {

    String message() default "更新渠道参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkChannelUpdateCheckValidator implements ConstraintValidator<SkChannelUpdateCheck, SkChannelUpdateReqParam> {

        @Override
        public boolean isValid(SkChannelUpdateReqParam updateReqParam, ConstraintValidatorContext context) {
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
            } else if (StringUtils.isEmpty(updateReqParam.getChannelName())
                    && (StringUtils.isEmpty(updateReqParam.getChannelCode()))) {
                message = "渠道名称和编号不能同时为空";
                valid = false;
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
