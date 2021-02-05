package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * sk_thumbs_up
 * @author 
 */
@Data
public class SkThumbsUp implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 点赞用户id
     */
    private Long userId;

    /**
     * 点赞类型（1-语音  2-视频）
     */
    private Byte thumbType;

    /**
     * 点赞资源id
     */
    private String mediaId;

    /**
     * 创建时间
     */
    private Date createTime;
    
    private Long mediaAuthorId;

    private static final long serialVersionUID = 1L;

  
}