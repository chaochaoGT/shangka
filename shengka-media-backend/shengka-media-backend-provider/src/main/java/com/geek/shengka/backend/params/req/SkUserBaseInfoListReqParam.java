package com.geek.shengka.backend.params.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/20 16:29
 */
@Data
@ApiModel
public class SkUserBaseInfoListReqParam extends BasePageReqParam implements Serializable {
    private static final long serialVersionUID = -4334828799428409951L;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    /**
     * 声咖号
     */
    @ApiModelProperty(value = "声咖号")
    private String skCode;
    /**
     * 用户来源（0-注册  1-系统导入小视频作者   2-系统导入语音作者  3-系统导入短视频作者  4-运营自己的虚假作者）
     */
    @ApiModelProperty(value = "用户来源（0-注册  1-系统导入小视频作者   2-系统导入语音作者  3-系统导入短视频作者  4-运营自己的虚假作者）")
    private Integer resource;

}
