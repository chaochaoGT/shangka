package com.geek.shengka.user.request;

import com.geek.shengka.common.basemodel.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 喜欢 和发布 入参
 *
 * @author: yunfei
 * @create: 2019-08-01 11:21
 **/
@Data
public class UserLikePushRequest extends PageRequest implements Serializable {

    /**
     * 用户
     */
    private Long userId;
}
