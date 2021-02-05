package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * sk_comment_notice
 * @author 
 */
@Entity
@Table(name="sk_comment_notice")
@Data
public class SkCommentNotice implements Serializable {
    /**
     * 主键
     */
	@Id
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 被@通知用户id
     */
    private Long noticeUserId;

    /**
     * 视频id
     */
    private String videoId;

    /**
     * 语音id
     */
    private String voiceId;

    /**
     * 通知类型（1-用户发语音评论视频  2-用户发视频@被通知人  3-用户语音@被通知人）
     */
    private Byte noticeType;

    /**
     * 通知状态（0-未查看  1-已查看  2-已删除）
     */
    private Byte noticeState;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 通知内容
     */
    private String noticeContent;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String userIcon;

    private static final long serialVersionUID = 1L;

    
}