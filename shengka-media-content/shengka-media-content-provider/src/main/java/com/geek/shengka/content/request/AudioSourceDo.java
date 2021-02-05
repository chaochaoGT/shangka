package com.geek.shengka.content.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AudioSourceDo implements Serializable {
    /** 唯一标识 **/
    @Id
    private String id;
    private String audioId;
    /** 视频id**/
    private String videoId;
    /** 用户编号**/
    private String userId;
    /** 用户昵称**/
    private String nickname;
    /** 标题**/
    private String title;
    /** 尺寸**/
    private String size;
    /**
     * 状态
     * 0：默认值，初始状态、1：正在初审、2：初审通过、3：初审未通过、6：正在终审、7：终审通过、8：终审未通过、9：撤销，10：用户删除  99：异常
     **/
    private String status = "0";
    /** 语音地址**/
    private String url;
    /** 语音类型**/
    private String  audioType;
    /** 是否已删除**/
    private Integer isDelete;
    /** 创建时间**/
    private Long gmtCreate;
    /**修改时间**/
    private Long gmtModified;
    /**创建人**/
    private String createBy;
    /**修改人**/
    private String modifiedBy;
    /**业务线**/
    private String bizLine;
    /** 主评论id 最多支持两级**/
    private String parentId;
    /**评论位置 排序使用**/
    private Integer commentPosition;
    /**评论点赞数**/
    private Integer  commentGiveThumbsNums;
    /**评论回复数**/
    private Integer  commentReplyNums;
    /**时长**/
    private int duration;
    /**地理位置**/
    private String geographic;

//    @Transient
//    private AudioSourceBizTypeEnum audioSourceBizTypeEnum;
}
