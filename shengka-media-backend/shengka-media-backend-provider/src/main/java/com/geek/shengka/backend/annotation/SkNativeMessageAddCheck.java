package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkNativeMessageEnableEnum;
import com.geek.shengka.backend.params.req.SkNativeMessageAddReqParam;
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
@Constraint(validatedBy = SkNativeMessageAddCheck.SkNativeMessageAddCheckValidator.class)
public @interface SkNativeMessageAddCheck {

    String message() default "新增站内信参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkNativeMessageAddCheckValidator implements ConstraintValidator<SkNativeMessageAddCheck, SkNativeMessageAddReqParam> {

        @Override
        public boolean isValid(SkNativeMessageAddReqParam addReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            if (addReqParam == null) {
                message = "Body参数不能为空!";
                valid = false;
            } else if (StringUtils.isEmpty(addReqParam.getMessageTitle())) {
                message = "标题不能为空!";
                valid = false;
            } else if (StringUtils.isEmpty(addReqParam.getMessageContent())) {
                message = "内容不能为空!";
                valid = false;
            } else if (addReqParam.getEnable() != null
                    && SkNativeMessageEnableEnum.isExist(addReqParam.getEnable())) {
                message = "是否有效参数异常，只能为有效或无效!";
                valid = false;
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
