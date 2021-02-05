package com.geek.shengka.common.basemodel;

import java.io.Serializable;

/**
 * API返回接口数据格式
 */
import com.geek.shengka.common.response.UserInfoResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 用户中心接口公共返回参数标准
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserTokenResponse implements Serializable {

    private static final long serialVersionUID = -4505655308965878999L;

    // 返回数据
    // 这里的数据， 可以是单个对象
    // 可以是map , collection
    // 也可以是分页对象 PageData
    // 也可以是基本数据类型
    private UserInfoResponse data;

    // 返回码
    private String code;

    // 返回描述
    private String msg;
    /**
     * 时间戳
     */
    private Long timestamp = System.currentTimeMillis();


    /**
     * 推荐使用封装的静态方法newXXX方法进行构造对象
     */
    public UserTokenResponse() {
    }


    public UserTokenResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
