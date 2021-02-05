package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkSearchPageConfigCodeEnum;
import com.geek.shengka.backend.enums.SkSearchPageConfigEnableEnum;
import com.geek.shengka.backend.enums.SkSearchPageConfigTypeEnum;
import com.geek.shengka.backend.params.req.SkSearchModuleMappingAddReqParam;
import com.geek.shengka.backend.params.req.SkSearchPageConfigAddReqParam;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author qubianzhong
 * @date 2019/8/5 10:43
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SkSearchPageConfigAddCheck.SkSearchPageConfigAddCheckValidator.class)
public @interface SkSearchPageConfigAddCheck {
    String message() default "新增搜索页参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkSearchPageConfigAddCheckValidator implements ConstraintValidator<SkSearchPageConfigAddCheck, SkSearchPageConfigAddReqParam> {

        @Override
        public boolean isValid(SkSearchPageConfigAddReqParam addReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            if (addReqParam == null) {
                message = "Body参数不能为空！";
                valid = false;
            } else if (StringUtils.isEmpty(addReqParam.getModuleName())) {
                message = "模块名称不能为空！";
                valid = false;
            } else if (StringUtils.isEmpty(addReqParam.getModuleDesc())) {
                message = "模块简介不能为空！";
                valid = false;
            } else if (addReqParam.getModuleType() == null) {
                message = "模块类型不能为空！";
                valid = false;
            } else if (StringUtils.isEmpty(addReqParam.getModuleCode())) {
                message = "模块编号不能为空！";
                valid = false;
            } else if (!SkSearchPageConfigCodeEnum.isExist(addReqParam.getModuleCode())) {
                message = "模块编号参数异常！只能为：声咖热搜、人气榜单、最热视频、发现精彩";
                valid = false;
            } else if (addReqParam.getModuleType() != null
                    && !SkSearchPageConfigTypeEnum.isExist(addReqParam.getModuleType())) {
                message = "模块类型参数异常！只能为：视频、话题";
                valid = false;
            } else if (addReqParam.getSeq() != null
                    && addReqParam.getSeq() < 1) {
                message = "模块排序不能小于1。";
                valid = false;
            } else if (addReqParam.getEnable() != null
                    && !SkSearchPageConfigEnableEnum.isExist(addReqParam.getEnable())) {
                message = "模块是否有效参数异常，只能为：有效、无效。";
                valid = false;
            } else if (!CollectionUtils.isEmpty(addReqParam.getModuleMappings())) {
                SkSearchModuleMappingAddReqParam mappingAddReqParam;
                int size = addReqParam.getModuleMappings().size();
                Set<Integer> seqSet = new HashSet<>(size);
                for (int i = 0; i < size; i++) {
                    mappingAddReqParam = addReqParam.getModuleMappings().get(i);
                    String str = "个关联的视频或话题元素中：";
                    if (mappingAddReqParam == null) {
                        message = "第" + (i + 1) + str + "不能为空！";
                        valid = false;
                    } else if (mappingAddReqParam.getModuleType() == null) {
                        message = "第" + (i + 1) + str + "类型不能为空！";
                        valid = false;
                    } else if (mappingAddReqParam.getSeq() == null) {
                        message = "第" + (i + 1) + str + "排序不能为空！";
                        valid = false;
                    } else if (mappingAddReqParam.getSeq() < 1) {
                        message = "第" + (i + 1) + str + "排序不能小于1！";
                        valid = false;
                    } else if (StringUtils.isEmpty(mappingAddReqParam.getSourceId())) {
                        message = "第" + (i + 1) + str + "映射资源ID不能为空！";
                        valid = false;
                    } else if (seqSet.contains(mappingAddReqParam.getSeq())) {
                        message = "第" + (i + 1) + str + "排序字段与前面不能重复！";
                        valid = false;
                    }
                    if (!valid) {
                        break;
                    }
                    seqSet.add(mappingAddReqParam.getSeq());
                }
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }
}
