package com.geek.shengka.gold.request;

import lombok.Builder;
import lombok.Data;

/**
 * 绑定身份证
 *
 * @author: yunfei
 * @create: 2019-06-07 15:05
 **/
@Data
@Builder
public class AccountBindingIdentityRequest {
    /**业务线*/
    private String bizCode;
    /**用户id*/
    private String userId;
    /**身份证号*/
    private String idCard;

    /**收款人姓名*/
    private String userName;

}
