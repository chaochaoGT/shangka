package com.geek.shengka.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qubianzhong
 * @Date 15:46 2019/8/20
 */
@Data
@ApiModel
public class SkPublishVideo implements Serializable {
    private static final long serialVersionUID = 6643010230578998513L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 发布者id
     */
    @ApiModelProperty(value = "发布者id")
    private Long publishUserId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 视频地址
     */
    @ApiModelProperty(value = "视频地址")
    private String videoUrl;

    /**
     * 封面地址
     */
    @ApiModelProperty(value = "封面地址")
    private String coverImageUrl;

    /**
     * 谁可以看(0-公开  1-好友   2-私密)
     */
    @ApiModelProperty(value = "谁可以看(0-公开  1-好友   2-私密)")
    private Byte conceal;

    /**
     * @用户id（多个id以“,”隔开）
     */
    @ApiModelProperty(value = "@用户id（多个id以“,”隔开）")
    private String noticeUserIds;

    /**
     * @用户信息集合（json格式：[{"userId":1, "nickName":"zhangsan"}]   ）
     */
    @ApiModelProperty(value = "@用户信息集合（json格式：[{\"userId\":1, \"nickName\":\"zhangsan\"}]   ）")
    private String noticeUserJson;

    /**
     * 话题id（多个id以“,”隔开）
     */
    @ApiModelProperty(value = "话题id（多个id以“,”隔开）")
    private String topicIds;

    /**
     * 关联话题集合（json格式：[{"id":1, "topicName":"话题1"}]  ）
     */
    @ApiModelProperty(value = "关联话题集合（json格式：[{\"id\":1, \"topicName\":\"话题1\"}]  ）")
    private String topicJson;

    /**
     * 0-审核中，1-审核通过， 2-审核失败,3-被删除
     */
    @ApiModelProperty(value = "0-审核中，1-审核通过， 2-审核失败,3-被删除")
    private Byte enable;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 观看方式：0，横屏1，竖屏
     */
    @ApiModelProperty(value = "观看方式：0，横屏1，竖屏")
    private Byte watchMode;

    /**
     * 内容中心类别id
     */
    @ApiModelProperty(value = "内容中心类别id")
    private Long conCategoryId;

}