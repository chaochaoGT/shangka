package com.geek.shengka.content.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * sk_voice
 * @author 
 */
@Entity
@Table(name="sk_voice")
@Data
public class SkVoice implements Serializable {
    /**
     * 主键
     */
	@Id
    private String id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 视频id
     */
    private String videoId;

    /**
     * 语音地址
     */
    private String voiceUrl;

    /**
     * 播放时长（单位：秒）
     */
    private Integer duration;
    
    /** 地理位置 **/
    private String geographic;

    /**
     * 0-审核中，1-审核通过， 2-审核失败
     */
    private Byte enable;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
 
}