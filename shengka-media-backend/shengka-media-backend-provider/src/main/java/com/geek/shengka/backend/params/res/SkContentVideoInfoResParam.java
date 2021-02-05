package com.geek.shengka.backend.params.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qubianzhong
 * @date 2019/8/6 9:38
 */
@Data
@ApiModel(value = "内容中心视频详情")
public class SkContentVideoInfoResParam implements Serializable {
    private static final long serialVersionUID = 8045101484476476174L;


    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private String id;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 视频封面
     */
    @ApiModelProperty(value = "视频封面")
    private String coverImage;
    /**
     * 视频文件地址
     */
    @ApiModelProperty(value = "视频文件地址")
    private String url;
    /**
     * 视频时长
     */
    @ApiModelProperty(value = "视频时长")
    private String duration;
    /**
     * 文件ID
     */
    @ApiModelProperty(value = "文件ID")
    private String fileId;
    /**
     * 点赞数
     */
    @ApiModelProperty(value = "点赞数")
    private String giveThumbsNums;
    /**
     *内容标记
     */
    @ApiModelProperty(value = "内容标记")
    private String contentType;
    /**
     *评论数
     */
    @ApiModelProperty(value = "评论数")
    private String commentNums;
    /**
     *收藏数
     */
    @ApiModelProperty(value = "收藏数")
    private String hasBeenCollected;
    /**
     *观看次数
     */
    @ApiModelProperty(value = "观看次数")
    private String watchedTimes;
    /**
     *达观推荐ID
     */
    @ApiModelProperty(value = "达观推荐ID")
    private String recRequestId;
    /**
     *大小
     */
    @ApiModelProperty(value = "大小")
    private String size;
    /**
     *权重分数
     */
    @ApiModelProperty(value = "权重分数")
    private String score;
    /**
     * 视频类型 0:短视频 1:小视频
     */
    @ApiModelProperty(value = "视频类型 0:短视频 1:小视频")
    private String watchMode;
    /**
     * 作者ID
     */
    @ApiModelProperty(value = "作者ID")
    private String authorId;
    /**
     * 作者名称
     */
    @ApiModelProperty(value = "作者名称")
    private String nickname;
    /**
     * 作者头像
     */
    @ApiModelProperty(value = "作者头像")
    private String avatar;
    /**
     *显示金币
     */
    @ApiModelProperty(value = "显示金币")
    private String showGold;
    /**
     *获取金币
     */
    @ApiModelProperty(value = "获取金币")
    private String gainGold;
}
