package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.params.req.SkChannelAddReqParam;
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
@Constraint(validatedBy = SkChannelAddCheck.SkChannelAddCheckValidator.class)
public @interface SkChannelAddCheck {

    String message() default "新增渠道参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkChannelAddCheckValidator implements ConstraintValidator<SkChannelAddCheck, SkChannelAddReqParam> {

        @Override
        public boolean isValid(SkChannelAddReqParam addReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            if (addReqParam == null) {
                message = "Body参数不能为空";
                valid = false;
            } else if (StringUtils.isEmpty(addReqParam.getChannelCode())) {
                message = "渠道编号不能为空";
                valid = false;
            } else if (StringUtils.isEmpty(addReqParam.getChannelName())) {
                message = "渠道名称不能为空";
                valid = false;
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
