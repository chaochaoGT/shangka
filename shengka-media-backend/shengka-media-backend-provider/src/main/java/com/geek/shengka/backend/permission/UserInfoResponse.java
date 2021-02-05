package com.geek.shengka.backend.permission;

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
     * 渠道号
     */
    private String channelCode;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 部门编号
     */
    private Integer departmentId;

    /**
     * 呢称
     */
    private String nickName;

    /**
     * 角色ID
     */
    private String roleIds;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
