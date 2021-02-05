package com.geek.shengka.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 视频举报入参
 *
 * @author: yunfei
 * @create: 2019-07-31 16:59
 **/
@Getter
@Setter
public class UserReportRequest implements Serializable {

    /**举报原因，用逗号拼接*/
    @NotNull
    private String reason;

    /**用户Id*/
    @NotNull
    private Long reportUserId;

}
