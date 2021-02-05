package com.geek.shengka.user.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 字典业务返回
 * @author  yunfei
 */
@Getter
@Setter
public class SkCodeResponse implements Serializable {
    /**
     * 主健
     */
    private Long id;

    /**
     * 编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;


}