package com.geek.shengka.gold.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 宝箱配置返回对象
 *
 * @author: yunfei
 * @create: 2019-08-01 15:06
 **/
@Getter
@Setter
public class TreasureBoxConfigResponse implements Serializable {


    /**
     * 累计时长（单位：秒）
     */
    private Integer watchDuration;

    /**
     * 宝箱配置ID
     */
    private Long configid;

    /**限制类型（1-次数限制   2-金币数限制）*/
    private Integer limitType;

    /**
     * 每日剩余领取次数上限/金币上限
     */
    private Integer limit;

    /**
     * 图标url
     */
    private String iconUrl;

}
