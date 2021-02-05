package com.geek.shengka.user.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/7 14:58
 */
@Data
public class SkUserCenterExtendVO implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 微信
     */
    private String weChat;

    /**
     * 业务线
     */
    private String userBizLine;

    /**
     * 个人签名
     */
    private String autograph;

    /**
     * 学校
     */
    private String school;

    /**
     * 生日
     */
    private String birthDay;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区
     */
    private String district;

}
