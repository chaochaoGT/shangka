package com.geek.shengka.gold.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 开启宝箱入参
 *
 * @author: yunfei
 * @create: 2019-08-12 16:19
 **/
@Getter
@Setter
public class OpenBoxRequest {

    private Long userId;

    /**宝箱配置ID*/
    private Long configId;
}
