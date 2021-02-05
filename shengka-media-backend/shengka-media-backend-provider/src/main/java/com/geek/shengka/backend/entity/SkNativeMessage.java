package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qubianzhong
 * @Date 10:19 2019/8/5
 */
@Data
@ApiModel(value = "站内信")
public class SkNativeMessage implements Serializable {
    private static final long serialVersionUID = -183371117773894329L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 消息标题
     */
    @ApiModelProperty(value = "消息标题")
    private String messageTitle;

    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String messageContent;

    /**
     * 0-有效  1-无效
     */
    @ApiModelProperty(value = "0-有效  1-无效", allowableValues = "0,1")
    private Byte enable;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;

}