package com.geek.shengka.backend.permission;

import lombok.Data;

import java.io.Serializable;

/**
 * API返回接口数据格式
 */
@Data
public class BaseApiResponse<T> implements Serializable {


    public static final String SUCCESS_CODE = "00000000";


    /**
     * 返回数据
     * 这里的数据， 可以是单个对象
     * 可以是map , collection
     * 也可以是分页对象 PageData
     * 也可以是基本数据类型
     */
    protected T data;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回描述
     */
    private String msg;

    /**
     * 推荐使用封装的静态方法newXXX方法进行构造对象
     */
    public BaseApiResponse() {
    }

    public BaseApiResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
