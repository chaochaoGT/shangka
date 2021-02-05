package com.geek.shengka.common.response;

import lombok.*;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserInfoResponse {


    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值，随机数
     */
    private String salt;

    /**
     * 推送令牌
     */
    private String lastToken;

    /**
     * 呢称
     */
    private String nickname;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 用户注册来源：1——iPhone；2——iPad；3——Android；4——微信；5——H5
     */
    private Integer source;

    /**
     * 客户头像
     */
    private String userAvatar;

    /**
     * 邀请人
     */
    private String inviter;

    /**
     * 状态:1;  正常  2;  锁定   3;  冻结
     */
    private Integer state;

    /**
     * 性别 1-男 2-女 3-未知
     */
    private Integer gender;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createMan;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifyMan;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业务线
     */
    private String userBizLine;

    /**
     * 微信
     */
    private String wechat;


    private String appId;

    private String sign;


    /**
     * 用户 token
     */
    private String token;

}
