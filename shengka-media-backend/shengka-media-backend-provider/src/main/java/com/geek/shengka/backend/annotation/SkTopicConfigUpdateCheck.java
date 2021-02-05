package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkTopicEnableEnum;
import com.geek.shengka.backend.enums.SkTopicTagEnum;
import com.geek.shengka.backend.params.req.SkTopicConfigUpdateReqParam;
import com.geek.shengka.backend.params.req.SkTopicVideoAddReqParam;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.stream.Collectors;

/**
 * @author qubianzhong
 * @date 2019/8/1 16:45
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SkTopicConfigUpdateCheck.SkTopicConfigUpdateCheckValidator.class)
public @interface SkTopicConfigUpdateCheck {

    String message() default "新增版本参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkTopicConfigUpdateCheckValidator implements ConstraintValidator<SkTopicConfigUpdateCheck, SkTopicConfigUpdateReqParam> {

        @Override
        public boolean isValid(SkTopicConfigUpdateReqParam updateReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            for (int k = 0; k < 1; k++) {
                if (updateReqParam == null) {
                    message = "Body参数不能为空!";
                    valid = false;
                } else if (updateReqParam.getId() == null) {
                    message = "ID不能为空";
                    valid = false;
                } else if (updateReqParam.getTopicTag() != null
                        && !SkTopicTagEnum.isExist(updateReqParam.getTopicTag())) {
                    message = "话题类型参数异常!";
                    valid = false;
                } else if (updateReqParam.getWatchCount() != null
                        && updateReqParam.getWatchCount() < 0) {
                    message = "初始播放次数不能小于0!";
                    valid = false;
                } else if (updateReqParam.getEnable() != null
                        && !SkTopicEnableEnum.isExist(updateReqParam.getEnable())) {
                    message = "是否有效参数异常!";
                    valid = false;
                } else if (!CollectionUtils.isEmpty(updateReqParam.getTopicVideos())) {
                    SkTopicVideoAddReqParam topicVideoAddReqParam;
                    int size = updateReqParam.getTopicVideos().size();
                    int setSize = updateReqParam.getTopicVideos().stream().map(SkTopicVideoAddReqParam::getVideoId).collect(Collectors.toSet()).size();
                    if (size != setSize) {
                        message = "话题关联视频中videoId包含重复数据";
                        valid = false;
                        break;
                    }
                    for (int i = 0; i < size; i++) {
                        topicVideoAddReqParam = updateReqParam.getTopicVideos().get(i);
                        String msgStart = "话题关联视频中第";
                        if (StringUtils.isEmpty(topicVideoAddReqParam.getVideoId())) {
                            message = msgStart + (i + 1) + "个中,视频ID不能为空";
                            valid = false;
                        } else if (topicVideoAddReqParam.getSeq() == null) {
                            message = msgStart + (i + 1) + "个中,排序不能为空";
                            valid = false;
                        } else if (topicVideoAddReqParam.getSeq() < 0) {
                            message = msgStart + (i + 1) + "个中,排序不能小于0";
                            valid = false;
                        }
                        if (!valid) {
                            break;
                        }
                    }
                }
            }

            //重新添加错误提示语句
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return valid;
        }
    }

}
