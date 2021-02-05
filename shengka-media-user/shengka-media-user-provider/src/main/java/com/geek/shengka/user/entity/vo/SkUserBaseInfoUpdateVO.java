package com.geek.shengka.user.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.geek.shengka.user.entity.SkUserBaseInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/7 11:33
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkUserBaseInfoUpdateVO implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 声咖号
     */
    private String skCode;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 背景图片
     */
    private String background;


    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String userIcon;

    /**
     * 1男 0女
     */
    private Integer gender;

    /**
     * 从用户中心获取的扩展信息
     */
    private SkUserCenterExtendVO extendVO;

}
