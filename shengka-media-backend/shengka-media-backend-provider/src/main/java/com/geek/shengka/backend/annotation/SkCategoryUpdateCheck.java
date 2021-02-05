package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkCategoryTypeEnum;
import com.geek.shengka.backend.params.req.SkCategoryMappingAddReqParam;
import com.geek.shengka.backend.params.req.SkCategoryUpdateReqParam;
import org.springframework.util.CollectionUtils;
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
@Constraint(validatedBy = SkCategoryUpdateCheck.SkCategoryUpdateCheckValidator.class)
public @interface SkCategoryUpdateCheck {

    String message() default "新增频道参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkCategoryUpdateCheckValidator implements ConstraintValidator<SkCategoryUpdateCheck, SkCategoryUpdateReqParam> {

        @Override
        public boolean isValid(SkCategoryUpdateReqParam updateReqParam, ConstraintValidatorContext context) {
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
            } else if (updateReqParam.getSeq() != null
                    && updateReqParam.getSeq() < 1) {
                message = "视频类别排序不能小于1";
                valid = false;
            } else if (StringUtils.isEmpty(updateReqParam.getCategoryName())) {
                message = "频道名称不能为空!";
                valid = false;
            } else if (updateReqParam.getCategoryType() != null
                    && !SkCategoryTypeEnum.isExist(updateReqParam.getCategoryType())) {
                message = "频道类型（1-默认  2-可选）参数错误!";
                valid = false;
            } else if (CollectionUtils.isEmpty(updateReqParam.getCategoryMappings())) {
                message = "视频类别列表不能为空!";
                valid = false;
            } else if (!CollectionUtils.isEmpty(updateReqParam.getCategoryMappings())) {
                int size = updateReqParam.getCategoryMappings().size();
                SkCategoryMappingAddReqParam cm;
                for (int i = 0; i < size; i++) {
                    cm = updateReqParam.getCategoryMappings().get(i);
                    if (cm.getContentCategoryId() == null) {
                        message = "视频类别列表第" + (i + 1) + "个元素中,类别ID不能为空!";
                        valid = false;
                    } else if (StringUtils.isEmpty(cm.getContentCategoryName())) {
                        message = "视频类别列表第" + (i + 1) + "个元素中,类别名称不能为空!";
                        valid = false;
                    }
                    if (!valid) {
                        break;
                    }
                }
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
