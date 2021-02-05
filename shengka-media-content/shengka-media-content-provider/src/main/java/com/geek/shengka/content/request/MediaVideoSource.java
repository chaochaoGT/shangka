package com.geek.shengka.content.request;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaVideoSource implements Serializable {

    /** 唯一标识 **/
    @Id
    private String id;
    private String videoId;
    /** lt观看数 **/
    private Integer ltWatchedTimes;
    /** lt收藏数  **/
    private Integer ltHasBeenCollected;
    /** lt点赞数  **/
    private Integer ltGiveThumbsNums;
    /** lt评论数 **/
    private Integer ltCommentNums;
    /** 观看方式：0，横屏1，竖屏 **/
    private Integer watchMode;
    /** 点赞数 **/
    private Integer giveThumbsNums;
    /** 评论数 **/
    private String commentNums;
    /** 格式化点赞数（万） **/
    private String formatedGiveThumbsNums;
    /** 被评论数 **/
    private String formattedCommentNums;
    /**收藏数**/
    private String hasBeenCollected;
    /** 观看次数**/
    private Integer watchedTimes;
    /** 格式化观看次数（万）**/
    private String formatWatchedTimes;
    /** 输入状态**/
    private String inputStatus;
    /** 星级**/
    private String starsMark;
    /** 推荐标识 **/
    private String commendLevel;
    /** 举报次数 **/
    private String reportTimes;
    /** 视频文件标识**/
    private String fileId;
    /** 标题**/
    private String title;
    /** 宽度**/
    private String width;
    /** 高度**/
    private String height;
    /** 时长**/
    private String duration;
    /** 地址**/
    private Addresses addresses;
    /** 封面图片Id **/
    private String coverImageId;
    /** 首个封面图片 **/
    private String firstCoverImage;
    /** 分享地址 **/
    private String shareUrl;
    /** 类别 **/
    private String category;
    /** 是否原始版本 **/
    private String isOriginal;
    /** 原始描述 **/
    private String isOriginalDesc;
    /** 内容类型**/
    private String contentType;
    /** 尺寸**/
    private String size;
    /** 作者ID **/
    private String authorId;
    /**
     * 状态  4：有效   5：失效   9：删除
     * 但是在将这个修改的时候，同时需要将is_delete的改成0：未删除，   1：删除
     **/
    private String status;
    /** 同步时间**/
    private String synchronizedTime;
    /** 同步多少天前 **/
    private String synchronizedAt;
    /** 类型 **/
    private String type;
    /** 封面图片**/
    private String coverImage;
    /** 作者**/
    private Author author;
    /** 是否已删除**/
    private Integer isDelete;
    /** 更新时间**/
    private Long modifiedTime;
    /** 创建时间**/
    private Long createTime;
    /** 时间戳(精确到秒)**/
    private String sequenceId;
    private Integer score;
    private String tags;
    private String chooseStatus;
    /**推送状态：0：待推送（预约推送中） 1：已推送  -1：推送失败**/
    private String isPush;
    /**
     * 标签ids
     */
    private String contentTags;
    /**
     *  有无标签:0：无 1：有
     **/
    private Integer hasTags;

    /**上传类型
     * 1：上传， 2：ugc，3：pgc
     **/
    private String uploadType;
    @Transient
    private Integer sort;
    @Transient
    private String url;
    @Transient
    private Long lwChannelId;
    @Transient
    private String collectionName;
    /**
     * 业务线
     */
    private String bizCode;

    /**
     * 主题
     */
    private String topic;
}
