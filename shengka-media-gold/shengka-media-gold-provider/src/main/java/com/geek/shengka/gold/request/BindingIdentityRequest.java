package com.geek.shengka.gold.request;

import lombok.Data;

/**
 *  绑定身份证入参
 * @author: yunfei
 * @create: 2019-06-04 20:24
 **/
@Data
public class BindingIdentityRequest {

    /**用户ID*/
    private long userId;

    /**身份证号码*/
    private String identityNo;

    /**收款人姓名*/
    private String userName;
}
