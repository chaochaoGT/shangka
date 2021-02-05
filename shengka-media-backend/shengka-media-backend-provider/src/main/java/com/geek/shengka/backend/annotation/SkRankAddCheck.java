package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkRankEnableEnum;
import com.geek.shengka.backend.enums.SkRankTypeEnum;
import com.geek.shengka.backend.params.req.SkRankAddReqParam;
import com.geek.shengka.backend.params.req.SkRankMappingAddReqParam;
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
@Constraint(validatedBy = SkRankAddCheck.SkRankAddCheckValidator.class)
public @interface SkRankAddCheck {

    String message() default "新增渠道参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkRankAddCheckValidator implements ConstraintValidator<SkRankAddCheck, SkRankAddReqParam> {

        @Override
        public boolean isValid(SkRankAddReqParam addReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            if (addReqParam == null) {
                message = "Body参数不能为空";
                valid = false;
            } else if (addReqParam.getSeq() != null
                    && addReqParam.getSeq() < 1) {
                message = "榜单排序不能小于1";
                valid = false;
            } else if (StringUtils.isEmpty(addReqParam.getRankName())) {
                message = "榜单名称不能为空";
                valid = false;
            } else if (StringUtils.isEmpty(addReqParam.getRankType())) {
                message = "榜单类型不能为空";
                valid = false;
            } else if (!SkRankTypeEnum.isExist(addReqParam.getRankType())) {
                message = "榜单类型参数异常，只能为：视频、用户、话题";
                valid = false;
            } else if (addReqParam.getEnable() != null
                    && !SkRankEnableEnum.isExist(addReqParam.getEnable())) {
                message = "榜单是否有效参数异常，只能为：启用、禁用";
                valid = false;
            } else if (addReqParam.getRankMappings() == null) {
                message = "榜单关联视频不能为空";
                valid = false;
            }
            if (addReqParam != null
                    && !CollectionUtils.isEmpty(addReqParam.getRankMappings())) {
                SkRankMappingAddReqParam srm;
                int size = addReqParam.getRankMappings().size();
                boolean rankTypeEqual;
                for (int i = 0; i < size; i++) {
                    srm = addReqParam.getRankMappings().get(i);
                    rankTypeEqual = addReqParam.getRankType().equals(srm.getRankType());
                    if (!rankTypeEqual) {
                        message = "榜单的类型和榜单关联视频中的类型不一致";
                        valid = false;
                    } else if (StringUtils.isEmpty(srm.getRelId())) {
                        message = "榜单关联视频中的视频ID不能为空";
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
