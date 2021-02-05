package com.geek.shengka.common.mq;

import com.geek.shengka.common.enums.UserBaseTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户基础数据消息
 *
 * @author: yunfei
 * @create: 2019-08-05 18:46
 **/
@Getter
@Setter
public class UserBaseDataMsg {

    /**更新基数*/
    private Integer num;

    /**用户Id*/
    private Long userId;

    /**更新参数code -取自 UserBaseTypeEnum*/
    private int code;
}
