package com.geek.shengka.gold.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 开启宝箱入参
 *
 * @author: yunfei
 * @create: 2019-08-01 16:55
 **/
@Getter
@Setter
public class TreasureBoxRequest implements Serializable {

    /**用户ID*/
    private Long userId;

    /**宝箱记录ID*/
    @NotNull
    private Long recordId;
}
