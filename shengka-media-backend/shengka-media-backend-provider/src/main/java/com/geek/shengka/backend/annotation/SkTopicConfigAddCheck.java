package com.geek.shengka.backend.annotation;

import com.geek.shengka.backend.enums.SkTopicTagEnum;
import com.geek.shengka.backend.params.req.SkTopicConfigAddReqParam;
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
@Constraint(validatedBy = SkTopicConfigAddCheck.SkTopicConfigAddCheckValidator.class)
public @interface SkTopicConfigAddCheck {

    String message() default "新增版本参数异常！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SkTopicConfigAddCheckValidator implements ConstraintValidator<SkTopicConfigAddCheck, SkTopicConfigAddReqParam> {

        @Override
        public boolean isValid(SkTopicConfigAddReqParam addReqParam, ConstraintValidatorContext context) {
            boolean valid = true;
            String message = "";
            //禁用默认的message的值
            context.disableDefaultConstraintViolation();
            for (int k = 0; k < 1; k++) {
                if (addReqParam == null) {
                    message = "Body参数不能为空!";
                    valid = false;
                } else if (StringUtils.isEmpty(addReqParam.getTopicName())) {
                    message = "话题名称参数不能为空!";
                    valid = false;
                } else if (StringUtils.isEmpty(addReqParam.getTopicIntro())) {
                    message = "话题简介参数不能为空!";
                    valid = false;
                } else if (StringUtils.isEmpty(addReqParam.getTopicLogo())) {
                    message = "话题logo参数不能为空!";
                    valid = false;
                } else if (StringUtils.isEmpty(addReqParam.getTopicTag())) {
                    message = "话题类型参数不能为空!";
                    valid = false;
                } else if (addReqParam.getWatchCount() == null) {
                    message = "初始播放次数不能为空!";
                    valid = false;
                } else if (addReqParam.getWatchCount() < 0) {
                    message = "初始播放次数不能小于0!";
                    valid = false;
                } else if (!SkTopicTagEnum.isExist(addReqParam.getTopicTag())) {
                    message = "话题类型参数异常!";
                    valid = false;
                } else if (!CollectionUtils.isEmpty(addReqParam.getTopicVideos())) {
                    SkTopicVideoAddReqParam topicVideoAddReqParam;
                    int size = addReqParam.getTopicVideos().size();
                    int setSize = addReqParam.getTopicVideos().stream().map(SkTopicVideoAddReqParam::getVideoId).collect(Collectors.toSet()).size();
                    if (size != setSize) {
                        message = "话题关联视频中videoId包含重复数据!";
                        valid = false;
                        break;
                    }
                    for (int i = 0; i < size; i++) {
                        topicVideoAddReqParam = addReqParam.getTopicVideos().get(i);
                        String msgStart = "话题关联视频中第";
                        if (StringUtils.isEmpty(topicVideoAddReqParam.getVideoId())) {
                            message = msgStart + (i + 1) + "个中,视频ID不能为空!";
                            valid = false;
                        } else if (topicVideoAddReqParam.getSeq() == null) {
                            message = msgStart + (i + 1) + "个中,排序不能为空!";
                            valid = false;
                        } else if (topicVideoAddReqParam.getSeq() < 0) {
                            message = msgStart + (i + 1) + "个中,排序不能小于0!";
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
