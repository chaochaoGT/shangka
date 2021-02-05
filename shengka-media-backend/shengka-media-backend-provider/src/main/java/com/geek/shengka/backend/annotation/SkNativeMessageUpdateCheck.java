package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkNativeMessageEnableEnum;
import com.geek.shengka.backend.params.req.SkNativeMessageUpdateReqParam;
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
@Constraint(validatedBy = SkNativeMessageUpdateCheck.SkNativeMessageAddCheckValidator.class)
public @interface SkNativeMessageUpdateCheck {

    String message() default "新增站内信参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkNativeMessageAddCheckValidator implements ConstraintValidator<SkNativeMessageUpdateCheck, SkNativeMessageUpdateReqParam> {

        @Override
        public boolean isValid(SkNativeMessageUpdateReqParam updateReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            if (updateReqParam == null) {
                message = "Body参数不能为空 ";
                valid = false;
            } else if (updateReqParam.getId() == null) {
                message = "ID不能为空 ";
                valid = false;
            } else if (StringUtils.isEmpty(updateReqParam.getMessageTitle())) {
                message = "标题不能为空";
                valid = false;
            } else if (StringUtils.isEmpty(updateReqParam.getMessageContent())) {
                message = "内容不能为空";
                valid = false;
            } else if (updateReqParam.getEnable() != null
                    && SkNativeMessageEnableEnum.isExist(updateReqParam.getEnable())) {
                message = "是否有效参数异常，只能为有效或无效!";
                valid = false;
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
