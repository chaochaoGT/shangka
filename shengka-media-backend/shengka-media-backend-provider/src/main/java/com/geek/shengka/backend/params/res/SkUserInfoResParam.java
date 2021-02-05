package com.geek.shengka.backend.params.res;

import com.geek.shengka.backend.enums.SkVersionAppTypeEnum;
import com.geek.shengka.backend.enums.SkVersionForcedUpdateEnum;
import com.geek.shengka.backend.enums.SkVersionPopupEnum;
import com.geek.shengka.backend.enums.SkVersionStateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/7 9:52
 */
@Data
@ApiModel(value = "用户中心-用户详情")
public class SkUserInfoResParam implements Serializable {
    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "用户编码")
    private String userCode;

    @ApiModelProperty(value = "电话")
    private String phoneNum;

    @ApiModelProperty(value = "呢称")
    private String nickname;

    @ApiModelProperty(value = "客户图像")
    private String userAvatar;

    @ApiModelProperty(value = "邀请人")
    private String inviter;

    @ApiModelProperty(value = "微信")
    private String wechat;

    @ApiModelProperty(value = "用户注册来源：1-iPhone；2-iPad；3-Android；4-微信；5-H5")
    private String source;

    @ApiModelProperty(value = "性别 1-男 2-女 3-未知")
    private String gender;

}
