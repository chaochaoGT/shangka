package com.geek.shengka.gold.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 开宝箱获得金币返回值
 *
 * @author: yunfei
 * @create: 2019-08-12 14:59
 **/
@Getter
@Setter
@Builder
public class GetCoinResponse {

    /**获得金币*/
    private Integer coin;

    /**记录ID*/
    private Long recordId;
}
