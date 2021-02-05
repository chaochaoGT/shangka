package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkSearchPageConfigCodeEnum;
import com.geek.shengka.backend.enums.SkSearchPageConfigEnableEnum;
import com.geek.shengka.backend.enums.SkSearchPageConfigTypeEnum;
import com.geek.shengka.backend.params.req.SkSearchModuleMappingAddReqParam;
import com.geek.shengka.backend.params.req.SkSearchPageConfigUpdateReqParam;
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
@Constraint(validatedBy = SkSearchPageConfigUpdateCheck.SkSearchPageConfigUpdateCheckValidator.class)
public @interface SkSearchPageConfigUpdateCheck {
    String message() default "新增搜索页参数异常。";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkSearchPageConfigUpdateCheckValidator implements ConstraintValidator<SkSearchPageConfigUpdateCheck, SkSearchPageConfigUpdateReqParam> {

        @Override
        public boolean isValid(SkSearchPageConfigUpdateReqParam updateReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            if (updateReqParam == null) {
                message = "Body参数不能为空。";
                valid = false;
            } else if (StringUtils.isEmpty(updateReqParam.getModuleName())) {
                message = "模块名称不能为空。";
                valid = false;
            } else if (StringUtils.isEmpty(updateReqParam.getModuleDesc())) {
                message = "模块简介不能为空。";
                valid = false;
            } else if (updateReqParam.getModuleType() == null) {
                message = "模块类型不能为空。";
                valid = false;
            } else if (updateReqParam.getModuleCode() != null
                    && !SkSearchPageConfigCodeEnum.isExist(updateReqParam.getModuleCode())) {
                message = "模块编号参数异常！只能为：声咖热搜、人气榜单、最热视频、发现精彩";
                valid = false;
            } else if (updateReqParam.getModuleType() != null
                    && !SkSearchPageConfigTypeEnum.isExist(updateReqParam.getModuleType())) {
                message = "模块类型参数异常！只能为：视频、话题。";
                valid = false;
            } else if (updateReqParam.getSeq() != null
                    && updateReqParam.getSeq() < 1) {
                message = "模块排序不能小于1。";
                valid = false;
            } else if (updateReqParam.getEnable() != null
                    && !SkSearchPageConfigEnableEnum.isExist(updateReqParam.getEnable())) {
                message = "模块是否有效参数异常，只能为：有效、无效。";
                valid = false;
            } else if (!CollectionUtils.isEmpty(updateReqParam.getModuleMappings())) {
                SkSearchModuleMappingAddReqParam mappingAddReqParam;
                int size = updateReqParam.getModuleMappings().size();
                Set<Integer> seqSet = new HashSet<>(size);
                for (int i = 0; i < size; i++) {
                    mappingAddReqParam = updateReqParam.getModuleMappings().get(i);
                    String str = "个关联的视频或话题元素中：";
                    if (mappingAddReqParam == null) {
                        message = "第" + (i + 1) + str + "不能为空。";
                        valid = false;
                    } else if (mappingAddReqParam.getModuleType() == null) {
                        message = "第" + (i + 1) + str + "类型不能为空。";
                        valid = false;
                    } else if (mappingAddReqParam.getSeq() == null) {
                        message = "第" + (i + 1) + str + "排序不能为空。";
                        valid = false;
                    } else if (mappingAddReqParam.getSeq() < 1) {
                        message = "第" + (i + 1) + str + "排序不能小于1。";
                        valid = false;
                    } else if (StringUtils.isEmpty(mappingAddReqParam.getSourceId())) {
                        message = "第" + (i + 1) + str + "映射资源ID不能为空。";
                        valid = false;
                    } else if (seqSet.contains(mappingAddReqParam.getSeq())) {
                        message = "第" + (i + 1) + str + "排序字段与前面不能重复。";
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
